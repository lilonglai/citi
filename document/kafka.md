set JAVA_HOME=C:\kevin\Java\jdk1.8.0_221
set PATH=%JAVA_HOME%\bin;%PATH%

set BASH_HOME=C:\cygwin64
set path=%BASH_HOME%\bin;%PATH%

cd C:\kevin\zookeeper-3.4.14
bin\zkServer.cmd


kafka
gradle releaseTarGz


cd C:\kevin\kafka_2.12-2.3.0

single

bin\windows\kafka-server-start.bat config\server.properties



bin\windows\kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 4 --topic test


bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic test

bin\windows\kafka-console-consumer.bat --group test-group --bootstrap-server localhost:9092 --topic test --from-beginning


cluster

bin\windows\kafka-server-start.bat config1\server.properties

bin\windows\kafka-server-start.bat config2\server.properties

bin\windows\kafka-server-start.bat config3\server.properties

bin\windows\kafka-topics --create --bootstrap-server localhost:9093 --replication-factor 1 --partitions 4 --topic test

bin\windows\kafka-console-producer.bat --broker-list localhost:9093 --topic test

bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9093 --topic test --from-beginning



debug
set KAFKA_DEBUG=Y
set DEBUG_SUSPEND_FLAG=y


org.apache.kafka.clients.consumer.KafkaConsumer


