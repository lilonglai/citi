pythoon
tar xzvf Python-2.7.16.tgz
cd Python-2.7.16
./configure --prefix=/home/kevin/app/python2.7

make
make install


pip2安装
curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py

python get-pip.py

/home/kevin/app/python2.7/bin/python /home/kevin/source/get-pip.py


export PATH=/home/kevin/app/python2.7/bin:$PATH

python charm
wget https://download.jetbrains.8686c.com/python/pycharm-professional-2019.2.tar.gz


