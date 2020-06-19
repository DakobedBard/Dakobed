import  boto3


def delete_snotel_table(dynamodb=None):
    if not dynamodb:
        dynamodb = boto3.resource('dynamodb', endpoint_url="http://localhost:8000")

    table = dynamodb.Table('Snotel')
    table.delete()

def create_snotel_table(dynamodb = None):
    try:
        resp = dynamodb.create_table(
            AttributeDefinitions=[
                {
                    "AttributeName": "Location",
                    "AttributeType": "S"
                },
                {
                    "AttributeName": "Date",
                    "AttributeType": "S"
                },
            ],
            TableName="Snotel",
            KeySchema=[
                {
                    "AttributeName": "Location",
                    "KeyType": "HASH"
                },
                {
                    "AttributeName": "Date",
                    "KeyType": "RANGE"
                }
            ],
            ProvisionedThroughput={
                "ReadCapacityUnits": 1,
                "WriteCapacityUnits": 1
            }
        )
        print("Table created successfully!")
    except Exception as e:
        print("Error creating table:")
        print(e)

dynamodb = boto3.resource('dynamodb', endpoint_url="http://localhost:8000")
create_snotel_table(dynamodb)

#delete_snotel_table(dynamodb)
# {
#     "AttributeName": "snow_current",
#     "AttributeType": "N"
# },
# {
#   "AttributeName": "snow_median",
#    "AttributeType": "N"
# },
#  {
#     "AttributeName": "snow_pct_median",
#      "AttributeType": "N"
#  },
# {
#     "AttributeName": "water_current",
#     "AttributeType": "N"
# },
# {
#     "AttributeName": "water_current_average",
#     "AttributeType": "N"
# },
# {
#     "AttributeName": "water_pct_average",
#     "AttributeType": "N"
# },