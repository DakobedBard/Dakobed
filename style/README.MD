python3 styletransfer.py img/bulb.png    img/style.jpg results/my_result

docker build . -t dakobed:style
docker run -it dakobed/style python3 uploadS3.py


docker build . -t dakobed:style
docker run  dakobed:style python