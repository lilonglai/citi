export APP_HOME=/home/kevin/app

# mysql
echo "mysql"
cd $APP_HOME/mysql5.6/usr/local/mysql
bin/mysqld_safe &
echo

# mongo
echo "mongo"
cd $APP_HOME/mongodb-linux-x86_64-4.0.10
bin/mongod --dbpath data &
echo

# redis
echo "redis"
cd $APP_HOME/redis5.0
bin/redis-server &
echo

# nginx
echo "nginx"
cd $APP_HOME/nginx1.17
sbin/nginx
echo

# rocket
echo "rocket"
cd $APP_HOME/rocketmq-all-4.5.1-bin-release
bin/mqnamesrv &
bin/mqbroker -n localhost:9876 &
echo

# zookeeper
echo "zookeeper"
cd $APP_HOME/zookeeper-3.4.10
bin/zkServer.sh start
echo

# es
echo "es"
cd $APP_HOME/elasticsearch-7.2.0
bin/elasticsearch -d
echo 



