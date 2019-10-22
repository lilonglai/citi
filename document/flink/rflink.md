# build and install
tar xzvf flink-1.9.0-src.tgz
mvn clean package -DskipTests

build-target
cp -r /kevin/source/flink-1.9.0/build-target flink-1.9.0
start-cluster.sh
StandaloneSessionClusterEntrypoint
TaskManagerRunner


# code analysis
jobmanager
ClusterEntrypoint
SessionClusterEntrypoint
StandaloneSessionClusterEntrypoint

TaskExecutor
TaskManagerRunner


org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
org.apache.flink.streaming.api.environment.StreamExecutionEnvironment#socketTextStream(java.lang.String, int, char, long)

org.apache.flink.streaming.api.datastream.DataStream

org.apache.flink.api.java.tuple.Tuple2
org.apache.flink.util.Collector
org.apache.flink.util.Collector#collect

org.apache.flink.streaming.api.datastream.KeyedStream


org.apache.flink.api.java.ExecutionEnvironment

