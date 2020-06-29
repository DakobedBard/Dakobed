import boto3

dynamoDB = boto3.client('dynamodb', region_name='us-west-2', endpoint_url='http://localhost:8000/')

def get_wav_midi_pair(pieceID):
    response = dynamoDB.get_item(
        TableName='MaestroPieces',
        Key={
            'PieceID': {'S': str(pieceID)},
        }
    )
    return response['Item']['audio_filename']['S'],response['Item']['midifile']['S']



wav, midi = get_wav_midi_pair(1)
print(wav)
print(midi)

# data/maestro/2008/MIDI-Unprocessed_03_R2_2008_01-03_ORIG_MID--AUDIO_03_R2_2008_wav--2.wav