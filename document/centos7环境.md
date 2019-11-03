##centos7 环境搭建##
### 用户配置###
- **root用户** : root/Bxy19890723
- **用户名创建** : kevin/Bxy19890723

### 防火墙###
- sudo systemctl stop firewalld
- sudo systemctl start firewalld.service
- sudo systemctl disable firewalld
- vi /etc/selinux/config
- SELINUX=disabled

###阿里云yum源###
- sudo mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo_bak 
- sudo curl -o /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
- sudo yum makecache
- sudo yum -y update


###docker安装###
- yum install -y epel-release
- sudo yum -y install docker
- yum install -y docker-compose
- sudo systemctl start docker
- sudo docker run hello-world
- sudo docker pull nginx
- sudo docker pull php
- sudo docker pull mysql
- sudo docker pull tomcat
- yum install wireshark
- sudo  yum install wireshark-gnome
- sudo yum install java-1.8.0-openjdk-devel.x86_64

###c++环境
- yum install -y cmake
- yum install -y gcc
- yum install -y gdb
- yum install -y gcc-c++
- yum install -y pcre-devel
- yum install -y zlib zlib-devel
- yum install -y openssl-devel
- yum install -y ncurses-devel
- yum -y install autoconf
- yum -y install libnl libnl-devel

- yum install -y cmake gcc gdb gcc-c++ pcre-devel zlib zlib-devel openssl-devel ncurses-devel autoconf


###目录配置###
- mkdir source
- mkdir source

tar xzvf keepalived-2.0.18.tar.gz
cd keepalived-2.0.18
./configure --prefix=/home/kevin/app/keepalived2.0 --exec-prefix=/home/kevin/app/keepalived2.0
make
make install


tar xzvf nginx-1.17.1.tar.gz
cd nginx-1.17.1
./configure --prefix=/home/kevin/app/nginx1.17
make
make install

启动(8000)
sbin/nginx
停止 sbin/nginx -s stop


mongodb
tar xzvf mongodb-linux-x86_64-4.0.10.tgz
mongodb-linux-x86_64-4.0.10

启动
bin/mongod --dbpath data

停止
bin/mongod --dbpath data  --shutdown


zookeeper
tar xzvf zookeeper-3.4.10.tar.gz
cd zookeeper-3.4.10

启动
bin/zkServer.sh start
停止
bin/zkServer.sh stop

rocketmq
rocketmq-all-4.5.1-bin-release.zip
cd rocketmq-all-4.5.1-bin-release

nameserver 9876
启动 bin/mqnamesrv&
停止 bin/mqshutdown namesrv

broker
启动  bin/mqbroker -n localhost:9876 &
停止 bin/mqshutdown broker



wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.2.0-linux-x86_64.tar.gz

./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.2.0/elasticsearch-analysis-ik-7.2.0.zip

bin/elasticsearch -d

ps -ef |grep redis|awk '{print $2}'|xargs kill -9


mkdir build
cd build

../configure --enable-checking=release --enable-languages=c,c++ --disable-multilib

make -j4

rpm -e gcc-4.8.5-36.el7_6.2.x86_64 gcc-c++-4.8.5-36.el7_6.2.x86_64

make install



pip install -U six
sudo pip install -U enum34 dnspython python-ldap --ignore-installed

sudo pip install -U  --ignore-installed requests
sudo pip install -U  --ignore-installed  PyYAML

