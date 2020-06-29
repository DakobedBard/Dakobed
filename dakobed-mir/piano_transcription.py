import boto3
import librosa
import numpy as np
import json
import matplotlib.pyplot as plt
from scipy.signal import find_peaks
import librosa.display

def get_wav_midi_notes(dynamoDB, pieceID):
    response = dynamoDB.get_item(
        TableName='MaestroPieces',
        Key={'PieceID': {'S': str(pieceID)},})
    notesJSONFile = 'data/dakobed-maestro/fileID{}/notes.json'.format(pieceID)
    with open(notesJSONFile) as f:
        notes = json.load(f)
    notes_array = [[note['time'],note['duration'], note['midi']] for note in notes]
    wavfile = response['Item']['audio_filename']['S']
    y, sr = librosa.load(wavfile)
    return y,sr,np.asarray(notes_array, dtype=np.float)


def notes_duration_histogram(notes, y,sr, plot=True):
    noteDurations = notes[:, 1]
    onset_env = librosa.onset.onset_strength(y, sr=sr)
    tempo = librosa.beat.tempo(onset_envelope=onset_env, sr=sr)
    npoints = 300
    time = np.linspace(0, 3, npoints)
    duration_histogram = np.histogram(noteDurations, time)
    durations_bins = duration_histogram[0]
    bins = duration_histogram[1][:npoints - 1]
    distance = (npoints*12)/200
    peaks, _ = find_peaks(durations_bins, height=0, distance=distance)
    if plot:
        plt.plot(bins, durations_bins)
        plt.plot(bins[peaks], durations_bins[peaks], "x")
        plt.show()
    return durations_bins, bins, peaks


def detect_tempo_for_audio_segment(audioseg, sample_rate, window, plot=True):
    tempo, beat_times = librosa.beat.beat_track(audioseg, sr=sample_rate, start_bpm=60, units='time')
    beat_times_diff = np.diff(beat_times)

    if plot:
        plt.figure(figsize=(14, 5))
        librosa.display.waveplot(audioseg, alpha=0.6)
        plt.vlines(beat_times, -1, 1, color='r')
        plt.ylim(-1, 1)
        plt.plot(window[:,0], [0]*len(window), 'x', color='black');
        plt.show()
        # plt.figure(figsize=(14, 5))
        # plt.hist(beat_times_diff, bins=50, range=(0, 4))
        # plt.xlabel('Beat Length (seconds)')
        # plt.ylabel('Count')

        plt.show()
    return tempo, beat_times,beat_times_diff

def splice_window_notes(notes, window_time_begin, window_time_end,i):
    window = []
    print("I get called w/ window begin {} window end {} and i {}".format(window_time_begin, window_time_end, i))
    for i in range(i, len(notes)):
        note = notes[i]
        if note[0] > window_time_begin and note[0] < window_time_end:
            window.append(note)
        if note[0] > window_time_end:
            break
    return np.asarray(window), i


# dynamoDB = boto3.client('dynamodb', region_name='us-west-2', endpoint_url='http://localhost:8000/')
# y,sr, notes = get_wav_midi_notes(dynamoDB,1)


nsamples = 20*sr
n20windows = y.shape[0]//nsamples
index = 0
windows = []

for i in range(1):
    window_notes, index = splice_window_notes(notes, 20*i, 20*(i+1), index)
    tempo, beat_times, beat_times_diff = detect_tempo_for_audio_segment(y[i * nsamples:(i * nsamples) + nsamples], sr,
                                                                        window_notes, True)
    windows.append(window_notes)

# for i in range(2):
#     notes_duration_histogram(y[i*nsamples:(i*nsamples) + nsamples])



