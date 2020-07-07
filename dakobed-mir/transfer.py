from keras.models import Sequential
from keras.layers import Dense, Dropout, Activation, Conv2D, MaxPool2D, Flatten
from math import floor
import os
import psutil


process = psutil.Process(os.getpid())
print(process.memory_info().rss)