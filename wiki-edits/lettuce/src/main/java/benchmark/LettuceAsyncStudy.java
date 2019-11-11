package benchmark;


import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1)
@Threads(100)
@State(Scope.Benchmark)
@Measurement(iterations = 2, time = 600, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class LettuceAsyncStudy {
    private static final int LOOP = 10;
    private StatefulRedisClusterConnection<String, String> connection;
    @Setup
    public void setup() {
        RedisURI node1 = RedisURI.create("192.168.11.128", 9001);

        RedisClusterClient clusterClient = RedisClusterClient.create(Arrays.asList(node1));
        connection = clusterClient.connect();
    }
    @Benchmark
    public void get() throws ExecutionException, InterruptedException {
        RedisAdvancedClusterAsyncCommands<String, String> commands = connection.async();
        List<RedisFuture<String>> redisFutureList = new ArrayList<>();
        for (int i = 0; i < LOOP; ++i) {
            RedisFuture<String> future = commands.get("a");
            redisFutureList.add(future);
            future.get();
        }
        redisFutureList.forEach(f -> {
            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(LettuceAsyncStudy.class.getSimpleName())
                .output("C:\\kevin\\source\\wiki-edits\\lettuce\\target\\benchmark\\lettuceAsync-Throughput.log").forks(1).build();
        new Runner(options).run();
    }
}
