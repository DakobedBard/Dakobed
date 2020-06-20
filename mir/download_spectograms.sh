#!/bin/bash
aws s3 cp  s3://$1/ data/. --recursive