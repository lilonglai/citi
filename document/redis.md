###redis安装###
tar xzvf redis-5.0.4.tar.gz

cd redis-5.0.4/

make


make PREFIX=/home/kevin/app/redis5.0 install

启动
bin/redis-server &

停止
bin/redis-cli shutdown


redis cluster
9001/bin/redis-server 9001/redis.conf
9002/bin/redis-server 9002/redis.conf
9003/bin/redis-server 9003/redis.conf
9004/bin/redis-server 9004/redis.conf
9005/bin/redis-server 9005/redis.conf
9006/bin/redis-server 9006/redis.conf


bin/redis-cli --cluster create 192.168.11.128:9001 192.168.11.128:9002 192.168.11.128:9003 192.168.11.128:9004 192.168.11.128:9005 192.168.11.128:9006 --cluster-replicas 1


bin/redis-cli --cluster --slave --master-id

bin/redis-cli add 192.168.11.128:9007 -p 9002

bin/redis-cli del 192.168.11.128:9007 -p 9002

bin/redis-cli reshard -p 9002
bin/redis-cli shutdown

bin/redis-cli -c -p 9001

bin/redis-cli --cluster check 127.0.0.1:9001



org.springframework.data.mongodb.core.MongoTemplate

org.springframework.data.redis.connection.jedis.JedisConnectionFactory

org.springframework.data.redis.core.RedisTemplate


org.springframework.data.elasticsearch.repository.ElasticsearchRepository

org.apache.rocketmq.spring.core.RocketMQTemplate

org.apache.rocketmq.spring.annotation.RocketMQMessageListener

org.apache.rocketmq.spring.core.RocketMQListener

org.springframework.data.elasticsearch.core.ElasticsearchTemplate

org.springframework.jdbc.core.JdbcTemplate


yum -y install nfs-utils rpcbind



chmod a+x pv1 pv2 pv3 pv4 pv5 pv6