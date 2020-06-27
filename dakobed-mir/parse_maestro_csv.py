import pandas as pd
import boto3


def create_maestro_pieces_table():
    dynamodb = boto3.resource('dynamodb', region_name='us-west-2', endpoint_url='http://localhost:8000/')
    try:
        resp = dynamodb.create_table(
            AttributeDefinitions=[
                {
                    "AttributeName": "ArtistID",
                    "AttributeType": "S" },
                {
                    "AttributeName": "Year",
                    "AttributeType": "S"
                },

            ],
            TableName="MaestroPieces",
            KeySchema=[
                {
                    "AttributeName": "ArtistID",
                    "KeyType": "HASH"
                },
                {
                    "AttributeName": "Year",
                    "KeyType": "RANGE"
                }
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
                "ArtistID": {"S": row['canonical_composer']},
                "Year":{"S" : str(row['year'])},
                "Title": {"S": row['canonical_title']},
                "midifile": {"S": row['midi_filename']},
                "audio_filename": {"S": row['audio_filename']},
                "split":{"S": row['split']},
            }
        )
    except Exception as e:
        print(e)
