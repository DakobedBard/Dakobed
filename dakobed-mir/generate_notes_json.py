import os
import jams
import json
import boto3


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


files = annotation_audio_file_paths()
s3 = boto3.client('s3')
bucket = 'dakobed-tabs'

for i, filePair in enumerate(files):
    wav = filePair[0]
    jam = filePair[1]
    notes = jam_to_notes_matrix(jam)
    jsonNotes = []
    for note in notes:
        jsonNotes.append({'time': note[0], 'duration': note[1], 'midi': round(note[2]), 'string': note[3]})
    with open('data/dakobed-tabs/fileID{}.json'.format(i), 'w') as outfile:
        json.dump(jsonNotes, outfile)
    new_notes_json = "fileID{}/fileID{}Notes.json".format(i,i)
    with open('data/dakobed-tabs/fileID{}.json'.format(i), "rb") as f:
        s3.upload_fileobj(f, bucket, "fileID{}/{}notes.json".format(i,i))
    with open(wav, "rb") as f:
        s3.upload_fileobj(f, bucket, "fileID{}/".format(i)+ wav.split('/')[-1])