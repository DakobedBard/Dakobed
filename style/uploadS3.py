import boto3
import logging
import ecs_logging

# Get the Logger
logger = logging.getLogger("app")
logger.setLevel(logging.DEBUG)

# Add an ECS formatter to the Handler
handler = logging.StreamHandler()
handler.setFormatter(ecs_logging.StdlibFormatter())
logger.addHandler(handler)

# Emit a log! :)
logger.debug("Example message!")



client = boto3.resource('s3')
s3 = boto3.resource('s3')
s3.meta.client.upload_file(Filename='./img/sword2.jpg', Bucket='dakobed', Key='sword2.jpg')
print("whatt")