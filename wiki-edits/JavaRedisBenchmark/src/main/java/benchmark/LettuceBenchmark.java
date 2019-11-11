package benchmark;


import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = Constants.warmupCount)
@Threads(100)
@State(Scope.Benchmark)
@Measurement(iterations = 5, time = 600, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class LettuceBenchmark {
    private static final int LOOP = 1;
    private StatefulRedisClusterConnection<String, String> connection;
    @Setup
    public void setup() {
        RedisURI node1 = RedisURI.create("192.168.11.128", 9001);

        RedisClusterClient clusterClient = RedisClusterClient.create(Arrays.asList(node1));
        connection = clusterClient.connect();
    }
    @Benchmark
    public void get() throws ExecutionException, InterruptedException {
        RedisAdvancedClusterCommands<String, String> commands = connection.sync();
        for (int i = 0; i < LOOP; ++i) {
            String key = "key_" + i;
            String value= "Hello World From SMaRT";
             commands.get(key);
        }
    }

    @Benchmark
    public void set() throws ExecutionException, InterruptedException {
        RedisAdvancedClusterCommands<String, String> commands = connection.sync();
        for (int i = 0; i < Constants.lookCount; ++i) {
            String key = "key_" + i;
            String value= "Hello World From SMaRT";
            commands.set(key, value);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(LettuceBenchmark.class.getSimpleName())
                .output("lettuceAsync-Throughput.log").build();
        new Runner(options).run();
    }
}
