#!/bin/bash
git clone https://github.com/MathiasDarr/Dakobed.git

aws s3 cp s3://dakobed-style/style_dir transfer --recursive
aws s3 cp s3://dakobed-style/