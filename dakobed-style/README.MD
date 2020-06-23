#Dakobed Neural Style Transfer


#### Steps for manual reproduct of Style Transfer
[<img src="images/baker_base.jpg" width="250"/>](images/baker_base.jpg)

<img src="https://dakobed-style.s3-us-west-2.amazonaws.com/sunrise.jpg" width="300" height="200">
<img src="https://dakobed-style.s3-us-west-2.amazonaws.com/style_dir/gp_base.jpg" width="300" height="200">

<img src="https://dakobed-style.s3-us-west-2.amazonaws.com/result.png" width="300" height="200">




Launch GPU EC2 instance

bash launch.sh
bash scripts/sshEC2.sh replace-with-ec2-instance ipv4 dns 



finally I have found how to log the intital user data scripts

logs seems to be saved at /var/log/
tee reads standard input and writes to standard output and one or more files..


#!/bin/bash -ex
exec > >(tee /var/log/user-data.log|logger -t user-data -s 2>/dev/console) 2>&1
echo BEGIN
date '+%Y-%m-%d %H:%M:%S'
echo END


FUCK YES