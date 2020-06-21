#!/bin/bash -ex
exec > >(tee /var/log/user-data.log|logger -t user-data -s 2>/dev/console) 2>&1
echo BEGIN
date '+%Y-%m-%d %H:%M:%S'
mkdir results
aws s3 cp s3://dakobed-style/style_dir transfer --recursive
aws s3 cp s3://dakobed-style/transfer.py transfer
aws s3 cp s3://dakobed-style/activate_tensorflow.py .


python3 activate_tensorflow.py
echo END