https://github.com/mongodb/mongo/wiki/Build-Mongodb-From-Source

export PATH=/home/kevin/app/python2.7/bin:$PATH
export PATH=/home/kevin/app/gcc8.3/bin:$PATH

pip2 install -r buildscripts/requirements.txt -i http://pypi.douban.com/simple/ --trusted-host pypi.douban.com

sudo yum install curl-devel

               CC='/home/kevin/app/gcc8.3/bin/gcc',
               CXX='/home/kevin/app/gcc8.3/bin/g++'

envDict添加上面的配置
env.PrependENVPath('LD_LIBRARY_PATH', '/home/kevin/app/gmp6.1.2/lib:/home/kevin/app/mpfr3.1.4/lib:/home/kevin/app/mpc1.0.2/lib:/home/kevin/app/isl0.18/lib')


python2 buildscripts/scons.py  --disable-warnings-as-errors all


export CC=/home/kevin/app/gcc8.3/bin/gcc
export CXX=home/kevin/app/gcc8.3/bin/g++
python2 buildscripts/scons.py --prefix=/home/kevin/app/mongodb4.0 install