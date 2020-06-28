import librosa
import numpy as np
import json
import matplotlib.pyplot as plt
from scipy.signal import find_peaks


def load_wave():
    y,sr = librosa.load("data/maestro/2008/MIDI-Unprocessed_10_R2_2008_01-05_ORIG_MID--AUDIO_10_R2_2008_wav--1.wav")
    return y,sr


def load_notes():
    with open("data/dakobed-maestro/fileID119/fileID119notes.json") as f:
        notes = json.load(f)
    notes_aray = []
    for note in notes:
        notes_aray.append([note['time'],note['duration'], note['midi']])
    return np.asarray(notes_aray, dtype=np.float)


notes = load_notes()
y,sr = load_wave()

noteDurations = notes[:,1]
duration_histogram = np.histogram(noteDurations, np.linspace(0,3,200))
durations_bins = duration_histogram[0]
bins = duration_histogram[1][:199]


peaks, _ = find_peaks(durations_bins, height=0, distance=18)
plt.plot(bins, durations_bins)

plt.plot(bins[peaks],durations_bins[peaks], "x")
plt.show()

#
# duration_histogram = np.histogram(noteDurations, np.linspace(0, 3, 200))
#
# durations_bins = duration_histogram[0]
# bins = duration_histogram[1][:199]
#
# peaks, _ = find_peaks(durations_bins, height=0, distance=18)
# plt.plot(bins, durations_bins)
#
# plt.plot(bins[peaks], durations_bins[peaks], "x")
# plt.show()
#
#




