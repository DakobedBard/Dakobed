import json
import boto3

def getGuitarSetData(event, context):
    dynamodb = boto3.resource('dynamodb', region_name='us-west-2')
    table = dynamodb.Table('Dakobed-GuitarSet')
    response = table.scan()
    responseObject = {}
    responseObject['statusCode'] = 200
    responseObject['headers'] = {}
    responseObject['headers']['Content-Type'] = "application/json"
    # responseObject["headers"]["Access-Control-Allow-Origin"]= "*"
    # responseObject["headers"]["Access-Control-Allow-Credentials"] = "true"
    # responseObject["headers"]["Access-Control-Allow-Headers"] = "ODTP_SESSION"
    # responseObject["headers"]["Access-Control-Allow-Methods"] = "GET, POST, PUT, DELETE"

    responseObject['body'] = json.dumps(response)

    return responseObject



