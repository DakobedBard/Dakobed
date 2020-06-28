import os
import numpy as np
import librosa
import boto3
import json
from mido import MidiFile, merge_tracks
import pandas as  pd


def generate_annotation_matrix(notes, frames):
    '''
    This function will return a one hot encoded matrix of notes being played
    The annotation matrix will start w/ note 25 at index 0 and go up to note 100
    The highest and lowest values that I saw in the annotations seemed to be arounnd 29-96 so give a little leeway
    :return:
    '''
    annotation_matrix = np.zeros((88, frames))
    for note in notes:
        starting_frame = librosa.time_to_frames(note[0])
        duration_frames = librosa.time_to_frames(note[0] + float(note[1]))
        note_value = note[2]
        annotation_matrix[note_value - 25][starting_frame:starting_frame + duration_frames] = 1
    return annotation_matrix.T


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


def seconds_per_tick(mid):
    '''
    Returns the second per tick for the midi files
    :param mid:
    :return:
    '''
    ticks_per_quarter = mid.ticks_per_beat
    microsecond_per_quarter = mid.tracks[0][0].tempo
    microsecond_per_tick = microsecond_per_quarter / ticks_per_quarter
    seconds_per_tick = microsecond_per_tick / 1000000
    return float(np.format_float_positional(seconds_per_tick, 5))


def extract_notes(midi_file):
    '''
    Return a list of notes extracted from the MIDI file
    :param midi_file:
    :return:
    '''
    mid = MidiFile(midi_file)
    spt = seconds_per_tick(mid)
    current_time = 0
    current_notes = set()
    notes = []
    for msg in merge_tracks(mid.tracks):
        current_time += msg.time
        if msg.type == 'note_on':
            if msg.velocity != 0:
                current_notes.add((msg.note, current_time, msg.velocity))
            else:
                note = remove_note(current_notes, msg.note)
                notes.append((msg.note, spt * note[1], spt * current_time,
                              np.format_float_positional(spt * (current_time - note[1]), 2), note[2]))
    return notes


def remove_note(notes_set, note_value):
    '''
    Removes a tuple from a set based on the value of it's first element.
    '''
    for note in notes_set:
        if note[0] == note_value:
            notes_set.remove(note)
            return note

def generate_midi_wav_file_pairs():
    folders  =['2013' ,'2011'] #,'2006' ] #, '2017','2015','2008', '2009', '2014', '2018', '2004']
    file_pairs = []

    for folder in folders:
        files = os.listdir("data/maestro/{}/".format(folder))
        files.sort()
        for i in range(0, len(files), 2):
            if files[i][:-4] == files[i + 1][:-3]:
                file_pairs.append((folder + '/' + files[i], folder + '/' + files[i + 1]))
    return file_pairs


def extract_notes_midi(midi_file):
    '''
    Return a list of notes extracted from the MIDI file

    Leave some dang comments!!! Output notes array will have midi value first, then tim

    onset time,,
    duration,
    midi value
    note velocity


    :param midi_file:
    :return:
    '''
    mid = MidiFile(midi_file)
    spt = seconds_per_tick(mid)
    current_time = 0
    current_notes = set()
    notes = []
    for msg in merge_tracks(mid.tracks):
        current_time += msg.time
        if msg.type == 'note_on':
            if msg.velocity != 0:
                current_notes.add((msg.note, current_time, msg.velocity))
            else:
                note = remove_note(current_notes, msg.note)
                notes.append((spt * note[1], np.format_float_positional(spt * (current_time - note[1]), 2), msg.note,note[2]))
    notes.sort(key=lambda x:x[0])
    return notes

# files = generate_midi_wav_file_pairs()
s3 = boto3.client('s3')
bucket = 'dakobed-maestro'

def process_midi_wav_file_pair(wav, midi, i ):
    os.mkdir('data/dakobed-maestro/fileID{}'.format(i))
    y, sr = librosa.load(wav)
    cqt = librosa.amplitude_to_db(
        np.abs(librosa.core.cqt(y, sr=sr, n_bins=252, bins_per_octave=36, fmin=librosa.note_to_hz('C2'), norm=1))).T
    notes = extract_notes_midi(midi)
    jsonNotes = []
    for note in notes:
        jsonNotes.append({'time': note[0], 'duration': note[1], 'midi': round(note[2]), 'velocity': note[3]})

    with open('data/dakobed-maestro/fileID{}/fileID{}notes.json'.format(i, i), 'w') as outfile:
        json.dump(jsonNotes, outfile)

    annotation = generate_annotation_matrix(notes, cqt.shape[0])

    for file, array, s3path in [('data/dakobed-maestro/fileID{}/cqt.npy'.format(i), cqt, 'fileID{}/cqt.npy'.format(i)),
                                ('data/dakobed-maestro/fileID{}/annotation.npy'.format(i), annotation, 'fileID{}/cqt.npy'.format(i))]:
        np.save(file, arr=array)
        with open(file, "rb") as f:
            s3.upload_fileobj(f, bucket, file)

    with open('data/dakobed-tabs/fileID{}.json'.format(i), "rb") as f:
        s3.upload_fileobj(f, bucket, "fileID{}/{}notes.json".format(i, i))
    with open(wav, "rb") as f:
        s3.upload_fileobj(f, bucket, "fileID{}/{}audio.wav".format(i, i))


def create_maestro_pieces_table():
    dynamodb = boto3.resource('dynamodb', region_name='us-west-2', endpoint_url='http://localhost:8000/')
    try:
        resp = dynamodb.create_table(
            AttributeDefinitions=[
                {
                    "AttributeName": "PieceID",
                    "AttributeType": "S" },


            ],
            TableName="MaestroPieces",
            KeySchema=[
                {
                    "AttributeName": "PieceID",
                    "KeyType": "HASH"
                },
            ],
            ProvisionedThroughput={
                "ReadCapacityUnits": 1,
                "WriteCapacityUnits": 1
            })

    except Exception as e:
        print(e)

#create_maestro_pieces_table()
maestro_df = pd.read_csv('maestro-v2.0.0.csv')

for i in range(1282):
    row = maestro_df.iloc[i]
    if row['year'] == 2018:
        continue
    dynamoDB = boto3.client('dynamodb', region_name='us-west-2',endpoint_url='http://localhost:8000/')
    print("Processing fileID {}".format(i))
    try:

        wav = 'data/maestro/' + row['audio_filename']
        midi = 'data/maestro/' + row['midi_filename']
        process_midi_wav_file_pair(wav, midi, i)

        dynamoDB.put_item(
            TableName="MaestroPieces",
            Item={
                "PieceID" : {"S":str(i)},
                "ArtistID": {"S": row['canonical_composer']},
                "Year":{"S" : str(row['year'])},
                "Title": {"S": row['canonical_title']},
                "midifile": {"S": midi},
                "audio_filename": {"S": wav},
                "split":{"S": row['split']},
            }
        )

    except Exception as e:
        print(e)
    if i ==3:
        break



