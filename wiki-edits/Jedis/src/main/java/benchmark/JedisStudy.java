package benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1)
@Threads(100)
@State(Scope.Thread)
@Measurement(iterations = 2, time = 600, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JedisStudy {
    private static final int LOOP = 1;
    JedisCluster jc;
    @Setup
    public void setup() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("192.168.11.128", 9001));
        jc = new JedisCluster(jedisClusterNodes);
    }
    @Benchmark
    public void get() {
        for (int i = 0; i < LOOP; ++i) {
            jc.get("a");
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(JedisStudy.class.getSimpleName())
                .output("C:\\kevin\\source\\wiki-edits\\Jedis\\target\\benchmark\\jedis-Throughput.log").forks(1).build();
        new Runner(options).run();
    }
}
