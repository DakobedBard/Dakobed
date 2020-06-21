#!/bin/bash -ex
exec > >(tee /var/log/user-data.log|logger -t user-data -s 2>/dev/console) 2>&1
echo BEGIN
date '+%Y-%m-%d %H:%M:%S'
mkdir results
aws s3 cp s3://dakobed-style/style_dir . --recursive
aws s3 cp s3://dakobed-style/transfer.py .
aws s3 cp s3://dakobed-style/subprocess.py .
aws s3 cp s3://dakobed-style/keras_test.py .



python3 /subprocess.py keras_test.py
echo END