import boto3
import os

for dir in ['data/train', 'data/test','data']:
    try:
        os.removedirs(dir)
    except Exception as e:
        pass

s3_resource = boto3.resource('s3')
bucketName = 'glaicerspectab'
bucket = s3_resource.Bucket(bucketName)

os.mkdir('data')
os.mkdir('data/test')
os.mkdir('data/train')

for fileID in range(360):
    print(fileID)
    prefix = 'train' if fileID < 291 else 'test'
    os.mkdir('data/{}/fileID{}'.format(prefix, fileID))
    path = 'data/{}/fileID{}'.format(prefix, fileID)
    cqtfile = '{}/fileid_{}/cqt.npy'.format(prefix, fileID)
    annotation_file = '{}/fileid_{}/annotation_label.npy'.format(prefix, fileID)
    print(cqtfile)
    bucket.download_file(cqtfile, path+'/cqt.npy')
    bucket.download_file(annotation_file, path+'/annotation.npy')