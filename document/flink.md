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

standalone cluster HA

Yarn cluster HA

org.apache.flink.api.common.functions.RuntimeContext


mount -t nfs 192.168.11.148:/root/nfs/flink /home/kevin/app/flink-1.9.0/data


org.apache.flink.streaming.runtime.streamrecord.StreamRecord