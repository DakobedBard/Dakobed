import os
import numpy as np
import librosa
import jams
import boto3
import json


def jam_to_notes_matrix(jam_file):
    annotation = (jams.load(jam_file)).annotations
    # annotation['value'] = annotation['value'].apply(round_midi_numers)
    lowE = annotation[1]
    A = annotation[3]
    D = annotation[5]
    G = annotation[7]
    B = annotation[9]
    highE = annotation[11]
    notes = []
    for i,string in enumerate([lowE, A, D,G,B, highE]):
        for datapoint in string.data:
            notes.append([datapoint[0], datapoint[1], datapoint[2], i])
    notes.sort(key=lambda x: x[0])
    return np.asarray(notes, dtype=np.float32)


def notes_matrix_to_annotation(notes, nframes):
    binary_annotation_matrix = np.zeros((48, nframes))
    full_annotation_matrix = np.zeros((48,nframes))
    for note in notes:
        print(note[0])
        starting_frame = librosa.time_to_frames(note[0])
        duration_frames = librosa.time_to_frames(note[1])
        note_value, string = int(note[2]), note[3]
        binary_annotation_matrix[note_value - 25][starting_frame:starting_frame + duration_frames] = 1
        full_annotation_matrix[note_value - 25][starting_frame:starting_frame + duration_frames] = note[3]

    return binary_annotation_matrix, full_annotation_matrix


def annotation_audio_file_paths(audio_dir='data/guitarset/audio/', annotation_dir='data/guitarset/annotations' ):

    audio_files = os.listdir(audio_dir)
    audio_files = [os.path.join(audio_dir, file) for file in audio_files]
    annotation_files = os.listdir(annotation_dir)
    annotation_files = [os.path.join(annotation_dir, file) for file in annotation_files]

    file_pairs = []

    for annotation in annotation_files:
        annotation_file = annotation.split('/')[-1].split('.')[0]
        for audio_file in audio_files:
            if audio_file.split('/')[-1].split('.')[0][:-4] == annotation_file:
                file_pairs.append((audio_file, annotation))
    return file_pairs


def process_wav_jam_pair(jam, wav, filedID):

    bucket = "dakobed-tabs"
    notes = jam_to_notes_matrix(jam)
    s3 = boto3.client('s3')
    jsonNotes = []
    for note in notes:
        jsonNotes.append({'time': note[0], 'duration': note[1], 'midi': round(note[2]), 'string': note[3]})
    with open('data/dakobed-tabs/fileID{}.json'.format(filedID), 'w') as outfile:
        json.dump(jsonNotes, outfile)
    new_notes_json = "fileID{}/fileID{}Notes.json".format(filedID,filedID)
    with open('data/dakobed-tabs/fileID{}.json'.format(filedID), "rb") as f:
        s3.upload_fileobj(f, bucket, "fileID{}/{}notes.json".format(filedID,filedID))
    with open(wav, "rb") as f:
        s3.upload_fileobj(f, bucket, "fileID{}/".format(i)+ wav.split('/')[-1])

    path_ = 'data/guitarset/fileID{}'.format(filedID)
    if not os.path.isdir(path_):
        os.mkdir(path_)

    y, sr = librosa.load(wav)
    cqt =  librosa.amplitude_to_db(np.abs(librosa.core.cqt(y, sr=sr, n_bins=144, bins_per_octave=36, fmin=librosa.note_to_hz('C2'), norm=1))).T
    notes = jam_to_notes_matrix(jam)
    binary_annotation_matrix, multivariable_annotation_matrix = (notes, y.shape[0])

    uploadfiles = [('data/guitarset/fileID{}/cqt.npy'.format(filedID),cqt), ('data/guitarset/fileID{}/binary_annotation.npy'.format(filedID), binary_annotation_matrix),
                   ('data/guitarset/fileID{}/multivariable_annotation.npy'.format(filedID), multivariable_annotation_matrix)]
    for file, array in uploadfiles:
        np.save(file, arr=array)
        with open(file, "rb") as f:
            s3.upload_fileobj(f, bucket, file)


def save_transforms_and_annotations():
    for fileID, filepair in enumerate(annotation_audio_file_paths()):
        process_wav_jam_pair(filepair[1], filepair[0], fileID)
        print("Processed filepair " + str(fileID))


save_transforms_and_annotations()
