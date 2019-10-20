　　账户与安全

　　优化器索引

　　通用表表达式

　　窗口函数

　　InnoDB 增强

　　JSON 增强

###下载###
wget  https://cdn.mysql.com//Downloads/MySQL-5.6/mysql-5.6.44.tar.gz
tar xzvf mysql-5.6.44.tar.gz
cd mysql-5.6.44
mkdir bld
cd bld
cmake ..
make
make install DESTDIR=/home/kevin/app/mysql5.6


./scripts/mysql_install_db --basedir=/home/kevin/app/mysql5.6/usr/local/mysql --datadir=/home/kevin/app/mysql5.6/usr/local/mysql/data


bin/mysql --defaults-file=my.cnf

启动
bin/mysqld_safe &

停止
bin/mysqladmin --defaults-file=./my.cnf -u root -pBxy19890723 shutdown


https://dl.bintray.com/boostorg/release/1.69.0/source/boost_1_69_0.tar.gz

unzip mysql-8.0.18.zip
cd mysql-8.0.18
cmake3 -D CMAKE_C_COMPILER=/home/kevin/app/gcc8.3/bin/gcc -D CMAKE_CXX_COMPILER=/home/kevin/app/gcc8.3/bin/g++ -DDOWNLOAD_BOOST=1 -DWITH_BOOST=../boost ..

rm -rf /lib64/libstdc++.so.6
ln -s /home/kevin/app/gcc8.3/lib64/libstdc++.so /lib64/libstdc++.so.6

rm -rf /lib64/libstdc++.so.6
ln -s libstdc++.so.6.0.19 /lib64/libstdc++.so.6


tar xzvf mysql-8.0.16.tar.gz
cd mysql-8.0.16

cmake3 -D CMAKE_C_COMPILER=/home/kevin/app/gcc8.3/bin/gcc -D CMAKE_CXX_COMPILER=/home/kevin/app/gcc8.3/bin/g++ -DDOWNLOAD_BOOST=1 -DWITH_BOOST=../boost ..

make
make install DESTDIR=/home/kevin/app/mysql8.0

export CMAKE_C_COMPILER=/usr/bin/gcc
export CMAKE_CXX_COMPILER=/usr/bin/g++

cmake -DDOWNLOAD_BOOST=1 -DWITH_BOOST=../boost ..


bin/mysqld --initialize --datadir=/home/kevin/app/mysql8.0/usr/local/mysql/data


ldd  bin/mysqld

export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/home/kevin/app/gcc8.3/lib64/lib

启动`
bin/mysqld_safe &

停止
bin/mysqladmin --defaults-file=./my.cnf -u root -pBxy19890723 shutdown

alter user user() identified by "Bxy19890723";

update user set host='%' where user='root';
flush privileges;

