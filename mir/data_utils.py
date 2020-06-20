import numpy as np


def load_transform_and_annotation(id, train = True, spectogram = 'cqt'):
    if train:
        path = '/data/train/' + 'fileid_' + str(id) + '/'
    else:
        path = '/datatest/'+'fileid_' + str(id) + '/'
    annotation_label = np.load(path+'annotation_label.npy')
    if spectogram == 'fft':
        spec = np.load(path+'stft.npy')
        return spec, annotation_label
    elif spectogram == 'cqt':
        cqt = np.load(path+'cqt.npy')
        return cqt, annotation_label