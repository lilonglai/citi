./configure --prefix=/kevin/app/protobuf-2.5.0

make
make check
make install

cp ~/source/apache-zookeeper-3.5.5/zookeeper-assembly/target/apache-zookeeper-3.5.5-bin.tar.gz ~/app/apache-zookeeper-3.5.5-bin.tar.gz


tar xzvf ~/app/apache-zookeeper-3.5.5-bin.tar.gz



hadoop
mvn clean package -Pdist,native -DskipTests -Dtar
cp /kevin/source/hadoop-3.2.1-src/hadoop-dist/target/hadoop-3.2.1.tar.gz /kevin/app/
etc/hadoop/core-site.xml
<configuration>
    <property>  
       <name>fs.default.name</name>  
       <value>hdfs://localhost:9000</value>  
   </property>
</configuration>

etc/hadoop/mapred-site.xml
<configuration>
    <property>
       <name>mapreduce.framework.name</name>
       <value>yarn</value>
   </property>
</configuration>

etc/hadoop/hdfs-site.xml
<configuration>
    <property>  
        <name>dfs.replication</name>  
        <value>1</value>  
    </property>  
    <property>   
         <name>dfs.permissions</name>   
         <value>false</value>   
    </property>  
    <property>  
       <name>dfs.namenode.name.dir</name>  
       <value>/kevin/app/hadoop-3.2.1/data/namenode</value>  
    </property>  
    <property>  
        <name>fs.checkpoint.dir</name>  
        <value>/kevin/app/hadoop-3.2.1/data/snn</value>  
    </property>  
    <property>  
        <name>fs.checkpoint.edits.dir</name>  
        <value>/kevin/app/hadoop-3.2.1/data/snn</value>  
    </property>  
       <property>  
       <name>dfs.datanode.data.dir</name>  
       <value>/kevin/app/hadoop-3.2.1/data/datanode</value>  
   </property>
</configuration>


bin/hdfs namenode -format
http://192.168.11.128:9870/dfshealth.html#tab-datanode
http://192.168.11.128:9864/datanode.html
http://192.168.11.128:8088/cluster
http://192.168.11.128:8042/node/node

zookeeper
mvn clean package -DskipTests
/kevin/source/apache-zookeeper-3.5.5/zookeeper-assembly/target/apache-zookeeper-3.5.5-bin.tar.gz  ./
bin/zkServer.sh  start

hbase
mvn clean package -DskipTests assembly:single
cp /kevin/source/hbase-2.2.1/hbase-assembly/target/hbase-2.2.1-bin.tar.gz ./


kafka
gradle releaseTarGz
cp /kevin/source/kafka-2.3.0-src/core/build/distributions/kafka_2.12-2.3.0.tgz ./


solr
ant ivy-bootstrap


es
gradle :distribution:archives:buildLinuxTar
gradle :distribution:archives:buildOssLinuxTar


/kevin/source/elasticsearch-7.4/distribution/archives/linux-tar/build/distributions/elasticsearch-7.4.1-SNAPSHOT-linux-x86_64.tar.gz

export C_INCLUDE_PATH=/kevin/app/protobuf-2.5.0/include
export CPLUS_INCLUDE_PATH=/kevin/app/protobuf-2.5.0/include
export LD_LIBRARY_PATH=/kevin/app/protobuf-2.5.0/lib
export LIBRARY_PATH=/kevin/app/protobuf-2.5.0/lib

flink
tar xzvf flink-1.9.0-src.tgz
mvn clean package -DskipTests

build-target
cp -r /kevin/source/flink-1.9.0/build-target flink-1.9.0
start-cluster.sh
StandaloneSessionClusterEntrypoint
TaskManagerRunner


export APP_HOME=/kevin/app
. /env.sh

echo "es"
cd $APP_HOME/elasticsearch-7.4.1-SNAPSHOT
bin/elasticsearch -d
echo 


echo "hadoop"
cd $APP_HOME/hadoop-3.2.1
sbin/start-all.sh
echo

echo "zookeeper"
cd $APP_HOME/apache-zookeeper-3.5.5-bin
bin/zkServer.sh start
echo

echo "hbase"
cd $APP_HOME/hbase-2.2.1
bin/start-hbase.sh
echo


echo "kafka"
cd $APP_HOME/kafka_2.12-2.3.0
bin/kafka-server-start.sh config/server.properties
echo



