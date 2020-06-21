import subprocess
import sys

subprocess.run('bash -c "source activate /home/ubuntu/anaconda3/envs/tensorflow_p36 && python3 {} " '.format(sys.argv[0]), shell=True)
