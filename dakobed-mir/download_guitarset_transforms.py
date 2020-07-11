import boto3
import os

for dir in ['data/train', 'data/test','data']:
    try:
        os.removedirs(dir)
    except Exception as e:
        pass

s3_resource = boto3.resource('s3')
bucketName = 'dakobed-guitarset'
bucket = s3_resource.Bucket(bucketName)


for fileID in range(360):
    print(fileID)
    os.mkdir('data/dakobed-guitarset/fileID{}/'.format(fileID))
    path = 'data/dakobed-guitarset/fileID{}'.format(fileID)
    cqtfile = 'data/dakobed-guitarset/fileID{}/cqt.npy'.format(fileID)
    annotation_file = 'data/dakobed-guitarset/fileID{}/binary_annotation_label.npy'.format(fileID)
    print(cqtfile)
    bucket.download_file(cqtfile, path+'/cqt.npy')
    bucket.download_file(annotation_file, path+'/annotation.npy')