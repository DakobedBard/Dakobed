import subprocess
import sys

__name__ == '__main__:
    subprocess.run('bash -c "source activate /home/ubuntu/anaconda3/envs/tensorflow_p36 && python3 transfer.py " '.format(sys.argv[0]), shell=True)
