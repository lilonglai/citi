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