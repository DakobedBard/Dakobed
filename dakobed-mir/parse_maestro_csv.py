import pandas as pd
import boto3


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


create_maestro_pieces_table()
maestro_df = pd.read_csv('maestro-v2.0.0.csv')


for i in range(1282):
    row = maestro_df.iloc[i]

    dynamoDB = boto3.client('dynamodb', region_name='us-west-2',endpoint_url='http://localhost:8000/')
    try:
        dynamoDB.put_item(
            TableName="MaestroPieces",
            Item={
                "PieceID" : {"S":str(i)},
                "ArtistID": {"S": row['canonical_composer']},
                "Year":{"S" : str(row['year'])},
                "Title": {"S": row['canonical_title']},
                "midifile": {"S": 'data/maestro/' + row['midi_filename']},
                "audio_filename": {"S": 'data/maestro/' + row['audio_filename']},
                "split":{"S": row['split']},
            }
        )
    except Exception as e:
        print(e)


for i, filePair in enumerate(files):
    wav = filePair[1]
    midi = filePair[0]
    print("Processing fileID {}".format(i))
    os.mkdir('data/dakobed-maestro/fileID{}'.format(i))
    y, sr = librosa.load(wav)
    cqt =  librosa.amplitude_to_db(np.abs(librosa.core.cqt(y, sr=sr, n_bins=252, bins_per_octave=36, fmin=librosa.note_to_hz('C2'), norm=1))).T

    new_notes_json = "fileID{}/fileID{}Notes.json".format(i, i)
    notes = extract_notes_midi(midi)
    jsonNotes = []
    for note in notes:
        jsonNotes.append({'time': note[0], 'duration': note[1], 'midi': round(note[2]), 'velocity': note[3]})

    with open('data/dakobed-maestro/fileID{}/fileID{}notes.json'.format(i,i), 'w') as outfile:
        json.dump(jsonNotes, outfile)

    annotation = generate_annotation_matrix(notes, cqt.shape[0])

    for file, array, s3path in [('data/dakobed-maestro/fileID{}/cqt.npy'.format(i),cqt,'fileID{}/cqt.npy'.format(i) ),
                                ('data/dakobed-maestro/fileID{}/annotation.npy'.format(i), annotation,'fileID{}/cqt.npy'.format(i))]:
        np.save(file, arr=array)
        with open(file, "rb") as f:
            s3.upload_fileobj(f, bucket, file)

    with open('data/dakobed-tabs/fileID{}.json'.format(i), "rb") as f:
         s3.upload_fileobj(f, bucket, "fileID{}/{}notes.json".format(i,i))
    with open(wav, "rb") as f:
         s3.upload_fileobj(f, bucket, "fileID{}/{}audio.wav".format(i,i))




def process_wav_midi_pair(midi, wav, filedID):

    bucket = "dakobed-tabs"
    notes = jam_to_notes_matrix(jam)
    s3 = boto3.client('s3')
    jsonNotes = []
    for note in notes:
        jsonNotes.append({'time': note[0], 'duration': note[1], 'midi': round(note[2]), 'string': note[3]})
    with open('data/dakobed-tabs/fileID{}.json'.format(filedID), 'w') as outfile:
        json.dump(jsonNotes, outfile)
    new_notes_json = "fileID{}/fileID{}Notes.json".format(filedID,filedID)
    with open('data/dakobed-tabs/fileID{}.json'.format(filedID), "rb") as f:
        s3.upload_fileobj(f, bucket, "fileID{}/{}notes.json".format(filedID,filedID))
    with open(wav, "rb") as f:
        s3.upload_fileobj(f, bucket, "fileID{}/".format(i)+ wav.split('/')[-1])

    path_ = 'data/guitarset/fileID{}'.format(filedID)
    if not os.path.isdir(path_):
        os.mkdir(path_)

    y, sr = librosa.load(wav)
    cqt =  librosa.amplitude_to_db(np.abs(librosa.core.cqt(y, sr=sr, n_bins=144, bins_per_octave=36, fmin=librosa.note_to_hz('C2'), norm=1))).T
    notes = jam_to_notes_matrix(jam)
    binary_annotation_matrix, multivariable_annotation_matrix = (notes, y.shape[0])

    uploadfiles = [('data/guitarset/fileID{}/cqt.npy'.format(filedID),cqt), ('data/guitarset/fileID{}/binary_annotation.npy'.format(filedID), binary_annotation_matrix),
                   ('data/guitarset/fileID{}/multivariable_annotation.npy'.format(filedID), multivariable_annotation_matrix)]
    for file, array in uploadfiles:
        np.save(file, arr=array)
        with open(file, "rb") as f:
            s3.upload_fileobj(f, bucket, file)


def save_transforms_and_annotations():
    for fileID, filepair in enumerate(annotation_audio_file_paths()):
        process_wav_midi_pair(filepair[1], filepair[0], fileID)
        print("Processed filepair " + str(fileID))