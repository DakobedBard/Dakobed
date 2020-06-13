import sys
import boto3
import botocore
import logging
from botocore.exceptions import ClientError
import os


    def download( object_name ):
        """Upload a file to an S3 bucket
        :param file_name: File to upload
        :param bucket: Bucket to upload to
        :param object_name: S3 object name. If not specified then file_name is used
        :return: True if file was downloaded, else False
        """
        s3 = boto3.client('s3')
        try:
            s3.download_file(self.bucket, object_name, 'mediafiles/download.mp3')
        except ClientError as e:
            logging.error(e)
            print(e)
            return False
        return True

    def delete(self, object_name):
        s3 = boto3.resource('s3')
        #return s3.get_bucket_acl(Bucket='basedjango')
        try:
            obj = s3.Object(self.bucket, object_name)
            print("The objec name " + object_name)
            obj.delete()
            #s3.delete_object(Bucket=self.bucket, Key=object_name)
        except ClientError as e:
            logging.error(e)
            print(e)
            return False
        return True


    def setBucketName(self, bucketname):
        self.bucket = bucketname
        client = boto3.client('iam')





def downloadDirectoryFroms3(bucketName,remoteDirectoryName):
    s3_resource = boto3.resource('s3')
    bucket = s3_resource.Bucket(bucketName)
    for object in bucket.objects.filter(Prefix = remoteDirectoryName):
        if not os.path.exists(os.path.dirname(object.key)):
            os.makedirs(os.path.dirname(object.key))
        bucket.download_file(object.key,remoteDirectoryName+'/' +  object.key, object.key)