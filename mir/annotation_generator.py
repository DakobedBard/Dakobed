from random import shuffle
import numpy as np
from librosa import time_to_frames


def generate_annotation_matrix(annotation, frames):
    '''
    This function will return a one hot encoded matrix of notes being played
    The annotation matrix will start w/ note 25 at index 0 and go up to note 100
    The highest and lowest values that I saw in the annotations seemed to be arounnd 29-96 so give a little leeway
    :return:
    '''
    annotation_matrix = np.zeros((48, frames))
    for i,note in enumerate(annotation):
        print(i)
        starting_frame = time_to_frames(note[1])
        duration_frames = time_to_frames(note[2] - note[1])
        note_value = note[0]
        print("note " + str(note.shape))
        print("note_value " + str(note[0]))
        for i in note:
            print(note)
        # annotation_matrix[note_value - 25][starting_frame:starting_frame + duration_frames] = 1

    return annotation_matrix.T


def load_transform_and_annotation(id, train = True, spectogram = 'cqt'):
    if train:
        path = 'data/train/fileID{}/'.format(id)
    else:
        path = 'data/test/fileID{}/'.format(id)
    annotation_label = np.load(path+'annotation.npy')
    if spectogram == 'fft':
        spec = np.load(path+'stft.npy')
        return spec, annotation_label
    elif spectogram == 'cqt':
        cqt = np.load(path+'cqt.npy')
        return cqt, annotation_label

x,y = load_transform_and_annotation(0)
annotation = generate_annotation_matrix(y, x.shape[0])


def save_transforms_and_annotations():
    '''
    Will possibly have to have some more advanced logic to handle the case where these directories do not exist yet... Such as when I move the training of the network to AWS....

    We are going to check if the file already exists in which case... we do not overwrite it..

    We are going to normalize the data ...

    '''
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

        if i >= 290:
            path = os.getcwd()
            path += '/data/spec_labels/test/'
