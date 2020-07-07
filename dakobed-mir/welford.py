from pytest import approx
import numpy as np
from generator import load_guitarset_transform_annotation


def welford_files(file_range=290, frequency_bins = 144):
    '''
    This method will compute the variance and the mean over a range of arrays which are loaded in files
    :param file_range:  Compute the variance over the first file_range number of files for each column in the spectograms
    :param cqt:
    :return:THe runn
    '''
    n = 0
    delta = np.zeros(frequency_bins)
    mean = np.zeros(frequency_bins)
    M2 = np.zeros(frequency_bins)
    fileids = set(range(file_range))
    # fileids.remove(835)
    while fileids:
        id_ = fileids.pop()
        print("file {}".format(id_))
        data, _ = load_guitarset_transform_annotation(id_, 'af')
        for row in data:
            n = n + 1
            delta = row - mean
            mean = mean + delta / n
            M2 = M2 + delta * (row - mean)
    welford_variance = M2 / (n - 1)
    return welford_variance, mean


def test_welford():
    '''
    Test that the calculated variances are accurate
    :return:
    '''
    spec1 = np.load('../train/fileID0/cqt.npy')
    spec2 = np.load('../train/fileID1/cqt.npy')
    concat = np.concatenate((spec1, spec2), axis=0)

    concat_variance = concat.var(axis=0)
    concat_mean = concat.mean(axis=0)
    var, mean = welford_files(2)
    assert var == approx(concat_variance, rel=1e-1)
    assert mean == approx(concat_mean, rel=1e-1)




