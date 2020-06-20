from keras.utils import Sequence
from random import shuffle
import numpy as np
from data_utils import load_transform_and_annotation
from process_annotations import generate_annotation_matrix


def guitarsetGenerator(batchsize, train=True):
    def init_file_queue():
        if train:
            fileQueue = list(range(291))
            fileQueue = set(fileQueue)
            fileQueue = list(fileQueue)
            shuffle(fileQueue)
        else:
            fileQueue = list(range(291,361))
            shuffle(fileQueue)
            fileQueue = set(fileQueue)
        return fileQueue

    def stitch(next_spec, next_annotation):
        '''
        This method will handle the case when the generator reaches the end of one spectogram and stitch together
        the samples from the next.
            Calculate how many samples of the next spectogram I need to grab. Then set the current_spectogra_index to this value
            This method will be called when the spectogram gets pulled off the queue requiring the need to stitch together the spectograms
        '''

        n_samples = batchsize + currentIndex - x.shape[0]  # Number of samples in next spectogram
        prev_n_samples = batchsize - n_samples  # Number of samples in the previous spectogram

        spec1 = x[-prev_n_samples:]
        spec2 = next_spec[:n_samples]
        batchx = np.concatenate((spec1, spec2), axis=0)

        annotation1 = y[-prev_n_samples:]
        annotation2 = next_annotation[:n_samples]
        batchy = np.concatenate((annotation1, annotation2), axis=0)

        return batchx, batchy, next_spec, next_annotation, n_samples

    def generate_windowed_samples(spec):
        '''
        This method creates the context window for a sample at time t, Wi-2, Wi-1, Wi, Wi+1,Wi+2
        '''
        windowed_samples = np.zeros((spec.shape[0], 5, spec.shape[1]))
        for i in range(spec.shape[0]):
            if i <= 1:
                windowed_samples[i] = np.zeros((5, spec.shape[1]))
            elif i >= spec.shape[0] - 2:
                windowed_samples[i] = np.zeros((5, spec.shape[1]))
            else:
                windowed_samples[i, 0] = spec[i - 2]
                windowed_samples[i, 1] = spec[i - i]
                windowed_samples[i, 2] = spec[i]
                windowed_samples[i, 3] = spec[i + 1]
                windowed_samples[i, 4] = spec[i + 2]
        return windowed_samples

    fileQueue = init_file_queue()
    fileID = fileQueue.pop()
    x, annotation = load_transform_and_annotation(fileID, spectogram='cqt')
    x = generate_windowed_samples(x)
    y = generate_annotation_matrix(annotation, x.shape[0])
    currentIndex = 0

    while True:
        if currentIndex > x.shape[0] - batchsize:
            if len(fileQueue) == 0:
                init_file_queue()
            next_spec_id = fileQueue.pop()
            # print("Processing the next fiel with id {}".format(next_spec_id))
            # print("Length of the queue is {}".format(len(fileQueue)))
            x, annoation = load_transform_and_annotation(next_spec_id, spectogram='cqt')
            nextSpec = generate_windowed_samples(x)
            batchx, batchy, x, y, currentIndex = stitch(nextSpec,
                                                        generate_annotation_matrix(annoation, nextSpec.shape[0]))
            yield batchx.reshape((batchx.shape[0], batchx.shape[1], batchx.shape[2], 1)), batchy
        else:
            batchx = x[currentIndex:currentIndex + batchsize]
            batchy = y[currentIndex:(currentIndex + batchsize)]
            currentIndex = currentIndex + batchsize
            yield batchx.reshape((batchx.shape[0], batchx.shape[1], batchx.shape[2], 1)), batchy
count = 0
while count < 10000:
    x,y = guitarsetGenerator(32)
    print("x y" + x.shape + " " + y.shape)
    count +=1


