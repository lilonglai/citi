package com.citi.gsp.smart.benchmark;

import org.openjdk.jmh.annotations.*;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Threads(Constants.threadCount)
@State(Scope.Thread)
@Fork(1)
@Warmup(iterations = Constants.warmupCount, time = 3)
@Measurement(iterations = 5, time = Constants.iterationTime)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JedisBenchmark {
    JedisCluster jc;
    @Setup
    public void setup() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        List<HostPort> hostPortList = Constants.getRedisServerList();
        for(HostPort hostPort : hostPortList){
            jedisClusterNodes.add(new HostAndPort(hostPort.getHost(), hostPort.getPort()));
        }
        jc = new JedisCluster(jedisClusterNodes);
    }
    @Benchmark
    public void get() {
        for (int i = 0; i < Constants.lookCount; ++i) {
            String key = "key_" + Constants.generateKey();
            jc.get(key);
        }
    }

    @Benchmark
    public void set() {
        for (int i = 0; i < Constants.lookCount; ++i) {
            String key = "key_" + Constants.generateKey();
            String value= "Hello World From SMaRT";
            jc.set(key, value);
        }
    }
}
