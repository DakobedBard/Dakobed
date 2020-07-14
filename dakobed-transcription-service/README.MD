# Dakobed Transcription Service


aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin 710339184759.dkr.ecr.us-west-2.amazonaws.com


aws cloudformation create-stack --stack-name DakobedCoreStack --capabilities CAPABILITY_NAMED_IAM  --template-body core.yml   

docker build -t dakobed-transcription-service .

