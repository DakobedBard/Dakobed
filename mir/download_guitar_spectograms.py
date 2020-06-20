import boto3
import os
import sys

bucket = sys.argv[0]

s3_resource = boto3.resource('s3')
bucketName = 'heyward-audio-tabs'
bucket = s3_resource.Bucket(bucketName)
for fileID in range(291):
    prefix = 'train' if fileID < 1000 else 'test'
    os.mkdir('{}/fileid_{}'.format(prefix, fileID))
    bucket.download_file('{}/fileid_{}/cqt.npy'.format(prefix, fileID), '{}/fileid_{}/cqt.npy'.format(prefix, fileID))
    bucket.download_file('{}/fileid_{}/annotation_label.npy'.format(prefix, fileID),'{}/fileid_{}/annotation.npy'.format(prefix, fileID))