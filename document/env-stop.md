export APP_HOME=/home/kevin/app

# mysql
echo "mysql"
cd $APP_HOME/mysql5.6/usr/local/mysql
bin/mysqladmin --defaults-file=./my.cnf -u root -pBxy19890723 shutdown
echo

# mongo
echo "mongo"
cd $APP_HOME/mongodb-linux-x86_64-4.0.10
bin/mongod --dbpath data  --shutdown 
echo

# redis
echo "redis"
cd $APP_HOME/redis5.0
bin/redis-cli shutdown
echo

# nginx
echo "nginx"
cd $APP_HOME/nginx1.17
sbin/nginx -s stop
echo

# rocket
echo "rocket"
cd $APP_HOME/rocketmq-all-4.5.1-bin-release
bin/mqshutdown namesrv
bin/mqshutdown broker
echo

# zookeeper
echo "zookeeper"
cd $APP_HOME/zookeeper-3.4.10
bin/zkServer.sh stop
echo


# es
echo "es"
cd $APP_HOME/elasticsearch-7.2.0
ps -ef |grep redis|awk '{print $2}'|xargs kill -9
echo