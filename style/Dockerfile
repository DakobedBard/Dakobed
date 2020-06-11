FROM  tensorflow/tensorflow:latest-gpu-jupyter

COPY tftest.py tftest.py
COPY img/style.jpg style.jpg
COPY img/sword2.jpg sword2.jpg
RUN mkdir results
RUN pip install keras
RUN pip install Pillow
RUN python3 tftest.py  sword2.jpg    style.jpg results/my_result

COPY
