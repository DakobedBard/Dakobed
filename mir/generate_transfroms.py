import os
import numpy as np
import librosa
import boto3
import jams
import pandas as pd


def jam_to_dataframes(jam_file):
    annotation = (jams.load(jam_file)).annotations
    # annotation['value'] = annotation['value'].apply(round_midi_numers)
    lowE = annotation[1]
    A = annotation[3]
    D = annotation[5]
    G = annotation[7]
    B = annotation[9]
    highE = annotation[11]
    return A
    # return {1: lowE.to_dataframe(), 2: A.to_dataframe(), 3: D.to_dataframe(),
    #         4: G.to_dataframe(), 5: B.to_dataframe(), 6: highE.to_dataframe()}


def tablature_dataframe(jam_file):
    '''
    This function will take the dataframes from the jam_to_dataframes function and will concatenate it into a single dataframe.
    '''
    jams_dictionary = jam_to_dataframes(jam_file)
    for df, string_dict in jams_dictionary.items():
        string_dict['value'] = string_dict['value'].apply

    frames = []
    for dataframe in jams_dictionary:
        jams_dictionary[dataframe].drop('confidence', axis=1, inplace=True)

        jams_dictionary[dataframe]['string'] = dataframe
        frames.append(jams_dictionary[dataframe])

    strings_df = pd.concat(frames)
    strings_df.sort_values('time', inplace=True)

    return strings_df


def annotation_audio_file_paths(audio_dir='data/guitarset/audio/', annotation_dir='data/guitarset/annotations' ):

    audio_files = os.listdir(audio_dir)
    audio_files = [os.path.join(audio_dir, file) for file in audio_files]

    annotation_files = os.listdir(annotation_dir)
    annotation_files = [os.path.join(annotation_dir, file) for file in annotation_files]

    #for file in annotation_files:
    #    print(file)

    file_pairs = []

    for annotation in annotation_files:
        annotation_file = annotation.split('/')[-1].split('.')[0]
        for audio_file in audio_files:
            if audio_file.split('/')[-1].split('.')[0][:-4] == annotation_file:
                file_pairs.append((audio_file, annotation))
                #print(audio_file, annotation_file)
    return file_pairs


def anonotation_matrix(annotation, song_duration, time_bins):
    '''
    This function will take an annotation and produce a vectorized annotation that will be used as a label.
    '''
    # This line of code below belongs elsewhere I will move it some point.

    time_slices = np.linspace(0, song_duration, time_bins)
    annotation['note_end'] = annotation['time'] + annotation['duration']
    annotation_array = np.zeros([48, time_bins])

    for index, row in annotation.iterrows():
        for i in range(time_slices.shape[0]):
            if row['time'] < time_slices[i]:
                note_start_index = i - 1
            if row['note_end'] < time_slices[i]:
                note_end_index = i
                # Now fill in the values of of the annotation array.  This should be vectorized looping is stupid
                for j in range(note_start_index, note_end_index):
                    midi_index = row['value'] - 25
                    annotation_array[midi_index][j] = 1
                break
    return annotation_array


def process_wav_jam_pair(jam_file, wav_file, filedID):

    path_ = 'fileID{}'.format(filedID)
    # s3 = boto3.client('s3')
    # bucket = 'maestro-transforms'
    fmin = librosa.note_to_hz('C2')
    if not os.path.isdir(path_):
        os.mkdir(path_)
    y, sr = librosa.load(wav_file)
    cqt =  librosa.amplitude_to_db(np.abs(librosa.core.cqt(y, sr=sr, n_bins=252, bins_per_octave=36, fmin=fmin, norm=1))).T
    annotation = jam_to_dataframes(jam_file)
    return annotation
    # # notes = extract_notes('{}/{}'.format(data_folder_, midi_file))
    # # notesDF = pd.DataFrame(notes)
    #
    # cqt_file = '{}/cqt.npy'.format(path_)
    # annotation_file = '{}/annotation.npy'.format(path_)
    # for file, array in [(cqt_file,cqt)]: # ,(spec_file, spec), (annotation_file, notesDF.values)]:
    #     np.save(file, arr=array)
    #     with open(file, "rb") as f:
    #         s3.upload_fileobj(f, bucket, file)

#
#
# def process_data(file_pairs):
#     bucket = 'maestro-transforms'
#     s3 = boto3.client('s3')
#
#     prefix = 'train'
#     for i, pair in enumerate(file_pairs):
#         if i> len(file_pairs) * 70:
#             prefix ='test'
#         if i> len(file_pairs) * 2:
#             break
#         # Try with just 4 octaves for the cqt...
#         path_ = '{}/filedID{}'.format(prefix, i)
#
#         if os.path.isdir(path_) == False:
#             os.mkdir(path_)
#         process_wav_midi_pair(pair, path_)
#         # key = "{}/file{}".format(prefix, i)
#         # url = 's3://{}'.format(bucket, key)
#         print('Done writing to {}')


def save_transforms_and_annotations():
    path = os.getcwd()
    path += '/data/spec_labels/train/'

    for i, filepair in enumerate(annotation_audio_file_paths()):

        print(i, flush=True)
        newpath = path + 'fileid_' + str(i) + '/'
        if os.path.isdir(newpath) == False:
            os.mkdir(newpath)

        y, sr = librosa.load(filepair[0])
        # Try with just 4 octaves for the cqt...
        fmin = librosa.note_to_hz('C2')
        cqt = np.abs(librosa.core.cqt(y, sr=sr, n_bins=120, bins_per_octave=24, fmin=fmin, norm=1)).T
        spec = librosa.amplitude_to_db(np.abs(librosa.stft(y)), ref=np.max).T

        annotation = tablature_dataframe(filepair[1])
        annotation = annotation.reset_index(drop=True)

        annotation_labels = anonotation_matrix(annotation, y.shape[0] / sr, spec.shape[0])
        annotation_labels = annotation_labels.T

        cqt_file = newpath + 'cqt.npy'
        spec_file = newpath + 'stft.npy'
        annotation_file = newpath + 'annotation_label.npy'

        if os.path.isfile(cqt_file) == False:
            np.save(cqt_file, arr=cqt)
        if os.path.isfile(spec_file) == False:
            np.save(spec_file, arr=spec)
        if os.path.isfile(annotation_file) == False:
            np.save(annotation_file, arr=annotation_labels)


