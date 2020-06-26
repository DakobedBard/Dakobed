import json
import boto3


def generate_guitar_tab(fileID):
    s3 = boto3.resource('s3')
    obj = s3.Object('dakobed-tabs', 'fileID{}/{}notes.json'.format(fileID, fileID))
    body = obj.get()['Body'].read()
    with open(body) as f:
        data = json.load(f)

    return data
a = '/data/mddarr/Dakobed/dakobed-mir/data/dakobed-tabs/fileID0.json'
tab = generate_guitar_tab(0)
