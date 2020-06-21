import boto3
import sys

# style_dir = sys.argv[0]
style_dir = 'style_dir'
bootstrap_script = '''
#!/bin/bash
git clone https://github.com/MathiasDarr/Dakobed.git
cd Dakobed/dakobed-style
aws s3 cp s3://dakobed/style_dir transfer --recursive
'''


ec2 = boto3.resource('ec2', region_name='us-west-2')
ami = 'ami-0a2363a9cff180a64'
# instance_type = 'p2.xlarge'
instance_type='t2.micro'
PemKey = 'corwin'
securityGroup = 'sg-08bffe0790ee1dbcc'

instance = ec2.create_instances(
    ImageId=ami,
    MinCount=1,
    MaxCount=1,
    KeyName=PemKey,
    InstanceInitiatedShutdownBehavior='terminate',
    IamInstanceProfile={'Name': 'S3fullaccess'},
    InstanceType=instance_type,
    SecurityGroupIds=[securityGroup],
    UserData='''
        #!/bin/bash
        git clone https://github.com/MathiasDarr/Dakobed.git
        cd Dakobed/dakobed-style
        aws s3 cp s3://dakobed/style_dir transfer --recursive
        '''
        )


