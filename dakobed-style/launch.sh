
aws ec2 run-instances --image-id ami-0a2363a9cff180a64 --security-group-ids sg-08bffe0790ee1dbcc \
 --user-data file://userdata.sh --instance-type p2.xlarge --key-name corwin  --iam-instance-profile Name=S3fullaccess
