import boto3

import boto3
import time
import os
style_dir = os.getenv('style_dir')
bootstrap_script = '''
#!/bin/bash
git clone https://github.com/MathiasDarr/Dakobed.git
cd Dakobed/dakobed-style
aws s3 cp s3://dakobed/${} transfer --recursive

'''.format(style_dir)


ec2 = boto3.resource('ec2', region_name='us-west-2')
ami = 'ami-01a4e5be5f289dd12'
instance_type = 'p2.xlarge'
PemKey = '/home/mddarr/.ssh/corwin.pem'

instance = ec2.create_instances(
    ImageId=ami,
    MinCount=1,
    MaxCount=1,
    KeyName=PemKey,
    InstanceInitiatedShutdownBehavior='terminate',
    IamInstanceProfile={'Name': 'S3fullaccess'},
    InstanceType=instance_type,
    SecurityGroupIds=['sg-03915a624fb5bf7bd'],
    UserData=bootstrap_script
)


