import json
import boto3

def getGuitarSetTrainingData(event, context):
    dynamodb = boto3.resource('dynamodb', region_name='us-west-2')
    table = dynamodb.Table('Dakobed-GuitarSet')
    response = table.scan()
    responseObject = {}
    responseObject['statusCode'] = 200
    responseObject['headers'] = {}
    responseObject['headers']['Content-Type'] = "application/json"
    responseObject['body'] = json.dumps(response)

    return responseObject



