import os
import jams
import numpy as np


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
    return notes
    #return np.asarray(notes, dtype=np.float32)

files = annotation_audio_file_paths()
jam = files[0][1]

notes = jam_to_notes_matrix(jam)
json_array = []
for note in notes:
    json_array.append({'time':note[0],'duration':note[1], 'midi':round(note[2]), 'string':note[3]})

import json
jamsArray = json.dumps(json_array)

with open('song1.json', 'w') as outfile:
    json.dump(json_array, outfile)

#, 'midi':round(notes[0][2]), 'string',notes[0][3]})

