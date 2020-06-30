import os
import boto3
import librosa
import numpy as np
import json
import matplotlib.pyplot as plt
from scipy.signal import find_peaks
import librosa.display
import jams
from numpy import format_float_positional


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


def detect_tempo_for_audio_segment(audioseg, sample_rate, window, plot=True):
    tempo, beat_times = librosa.beat.beat_track(audioseg, sr=sample_rate, start_bpm=60, units='time')
    beat_times_diff = np.diff(beat_times)

    if plot:
        plt.figure(figsize=(14, 5))
        librosa.display.waveplot(audioseg, alpha=0.6)
        plt.vlines(beat_times, -1, 1, color='r')
        plt.ylim(-1, 1)
        plt.plot(window, [0]*len(window), 'x', color='black');
        plt.show()
    return tempo, beat_times, beat_times_diff


def splice_window_notes(notes, window_time_begin, window_time_end,i):
    window = []
    for i in range(i, len(notes)):
        note = notes[i]
        if note > window_time_begin and note< window_time_end:
            window.append(note)
        if note > window_time_end:
            break
    return np.asarray(window), i





def beat_audio_process(y, sr, notes):
    seconds_per_window = 20
    nsamples = seconds_per_window * sr
    n10windows = y.shape[0] // nsamples+1
    index = 0
    windows = []
    means = []

    for i in range(n10windows):
        window_note_times, index = splice_window_notes(notes, seconds_per_window * i, seconds_per_window * (i + 1), index)
        swindow = i*nsamples
        ewindow = (i*nsamples) + nsamples
        ywindow = y[swindow:ewindow]
        tempo, beat_times, beat_times_diff = detect_tempo_for_audio_segment(
             ywindow, sr, window_note_times - (seconds_per_window * i), True)
        means.append(beat_times_diff.mean())
        windows.append(window_note_times)

    beat_times_array = np.array([])
    for window in windows:
        beat_times_array = np.concatenate((beat_times_array, window))
    return beat_times_array, means


def plot_full_waveform_beats_notes(y,sr,notes):
    librosa.display.waveplot(y, alpha=0.6)
    tempo, beat_times = librosa.beat.beat_track(y, sr=sr, start_bpm=60, units='time')
    beats_list = [float(format_float_positional(beat, 3)) for beat in beat_times]
    beats = np.asarray(beats_list)
    beat_times_diff = np.diff(beat_times)
    plt.figure(figsize=(14, 5))
    librosa.display.waveplot(y, alpha=0.6)
    plt.vlines(beats, -1, 1, color='r')
    plt.ylim(-1, 1)
    plt.plot(notes, [0] * len(notes), 'x', color='black');
    plt.show()


files = annotation_audio_file_paths()
wav = files[2][0]
jam = files[2][1]

y, sr = librosa.load(wav)
notes = jam_to_notes_matrix(jam)
#plot_full_waveform_beats_notes(y,sr, notes[:,0])

tempo, beat_times = librosa.beat.beat_track(y, sr=sr, start_bpm=60, units='time')
beats_list = [float(format_float_positional(beat, 3)) for beat in beat_times]
beats = np.asarray(beats_list)

# class GuitarTab():
#     def __init__(self, notes, beats):
#
#         measures_array = [[] for i in range(nmeasures)]
#         current_beat_time = beats[0]
#         current_note_index = 0
#         for i, note in enumerate(beats):
#             if



measures_end_times = beats[0::4]
nmeasures = len(beats)//4
if len(beats) % 4 != 0:
    nmeasures +=1

print("There are nmeansures {}".format(nmeasures))
print("The numbber of measures is {} ".format(measures_end_times.shape))
measures = [[] for i in range(nmeasures)]
measure_index = 0
current_measure_end = measures_end_times[measure_index]

for note in notes:
    note[0] = format_float_positional(note[0],2)

for note in notes:
    if note[0] > current_measure_end:
        measure_index += 1
        if measure_index  >= len(measures):
            break
        measures[measure_index].append((note))
        current_measure_end = measures_end_times[measure_index]
    elif measure_index >= len(measures):
        break
    else:
        measures[measure_index].append(list(note))





# plt.plot(notes[:,0], [0]*len(notes), 'x', color='black');
# beats, means =  beat_audio_process(y, sr, notes[:,0])


# beats_list  = [float(format_float_positional(beat, 3)) for beat in beats]
# beats = np.asarray(beats_list)
#plt.show()


