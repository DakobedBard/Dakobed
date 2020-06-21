#!/bin/bash
git clone https://github.com/MathiasDarr/Dakobed.git
cd Dakobed/dakobed-style
aws s3 cp s3://dakobed/style_dir transfer --recursive