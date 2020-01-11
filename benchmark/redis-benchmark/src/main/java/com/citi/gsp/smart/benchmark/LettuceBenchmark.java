package com.citi.gsp.smart.benchmark;


import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.citi.gsp.smart.benchmark.Constants.iterationTime;

@BenchmarkMode(Mode.Throughput)
@Threads(Constants.threadCount)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = Constants.warmupCount, time = 3)
@Measurement(iterations = 5, time = iterationTime)
@OutputTimeUnit(TimeUnit.SECONDS)
public class LettuceBenchmark {
    private StatefulRedisClusterConnection<String, String> connection;
    @Setup
    public void setup() {
        List<RedisURI> redisURIS = new ArrayList<>();
        List<HostPort> hostPortList = Constants.getRedisServerList();
        for(HostPort hostPort: hostPortList) {
            redisURIS.add(RedisURI.create(hostPort.getHost(), hostPort.getPort()));
        }
        RedisClusterClient clusterClient = RedisClusterClient.create(redisURIS);
        connection = clusterClient.connect();
    }
    @Benchmark
    public void get() {
        RedisAdvancedClusterCommands<String, String> commands = connection.sync();
        for (int i = 0; i < Constants.lookCount; ++i) {
            String key = "key_" + Constants.generateKey();
             commands.get(key);
        }
    }

    @Benchmark
    public void set() {
        RedisAdvancedClusterCommands<String, String> commands = connection.sync();
        for (int i = 0; i < Constants.lookCount; ++i) {
            String key = "key_" + Constants.generateKey();
            String value= "Hello World From SMaRT";
            commands.set(key, value);
        }
    }
}
