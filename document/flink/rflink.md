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
org.apache.flink.streaming.api.datastream.WindowedStream


org.apache.flink.api.java.DataSet

org.apache.flink.api.java.operators.MapOperator
org.apache.flink.api.java.operators.FilterOperator
org.apache.flink.api.java.operators.SingleInputUdfOperator

org.apache.flink.api.java.operators.SingleInputOperator
org.apache.flink.api.java.operators.AggregateOperator

org.apache.flink.api.java.operators.Operator
org.apache.flink.api.java.DataSet



org.apache.flink.api.java.operators.ReduceOperator



org.apache.flink.api.java.operators.DistinctOperator


org.apache.flink.api.java.operators.UnsortedGrouping



org.apache.flink.api.java.operators.UnionOperator


org.apache.flink.api.java.operators.PartitionOperator



DataStream

org.apache.flink.streaming.api.datastream.KeyedStream
org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator

