import os
import boto3
import librosa
import numpy as np
import json
import matplotlib.pyplot as plt
from scipy.signal import find_peaks
import librosa.display


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


def detect_tempo_for_audio_segment(audioseg, sample_rate, plot=True):
    tempo, beat_times = librosa.beat.beat_track(audioseg, sr=sample_rate, start_bpm=60, units='time')
    print("audioshape: " + str(audioseg.shape))
    if plot:
        plt.figure(figsize=(14, 5))
        librosa.display.waveplot(audioseg, alpha=0.6)
        plt.vlines(beat_times, -1, 1, color='r')
        plt.ylim(-1, 1)
        plt.show()
        beat_times_diff = np.diff(beat_times)
        plt.figure(figsize=(14, 5))
        plt.hist(beat_times_diff, bins=50, range=(0, 4))
        plt.xlabel('Beat Length (seconds)')
        plt.ylabel('Count')

        plt.show()
    return tempo, beat_times

# files = annotation_audio_file_paths()
# wav = files[0][0]
# y, sr = librosa.load(wav)

nsamples = 20*sr
n20windows = y.shape[0]//nsamples
tempo, beat_times = librosa.beat.beat_track(y, sr=sr, start_bpm=60, units='time')
print("BEAT TIMES")
print(beat_times)
# plt.figure(figsize=(14, 5))
# librosa.display.waveplot(y, alpha=0.6)
# plt.show()
for i in range(2):
    tempo, beat_times = detect_tempo_for_audio_segment(y[i*nsamples:(i*nsamples) + nsamples], sr, plot = False)
    print(beat_times)
    print(tempo)

# nsamples = 20*sr
# n20windows = y.shape[0]//nsamples
#
# for i in range(2):
#     tempo, beat_times = detect_tempo_for_audio_segment(y[i*nsamples:(i*nsamples) + nsamples], sr, plot = True)
#     print(tempo)
#
#
#
# beat_times_diff = np.diff(beat_times)
# plt.figure(figsize=(14, 5))
# plt.hist(beat_times_diff, bins=50, range=(0,4))
# plt.xlabel('Beat Length (seconds)')
# plt.ylabel('Count')
