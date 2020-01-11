package benchmark;

import com.alibaba.fastjson.JSON;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = Constants.warmupCount)
@Threads(100)
@State(Scope.Thread)
@Measurement(iterations = 5, time = 600, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JedisBenchmark2 {
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
        for (int i = 0; i < Constants.lookCount; ++i) {
            UUID uuid = UUID.randomUUID();
            String key = "benchmark_" + uuid.toString();
            jc.get(key);
        }
    }

    @Benchmark
    public void set() {
        for (int i = 0; i < Constants.lookCount; ++i) {
            UUID uuid = UUID.randomUUID();
            String key = "benchmark_" + uuid.toString();
            TestValue  testValue = new TestValue();
            testValue.setId(uuid.toString());
            String value = JSON.toJSONString(testValue);
            jc.set(key, value);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(JedisBenchmark2.class.getSimpleName())
                .output("jedis-Throughput.log").build();
        new Runner(options).run();
    }

    static class TestValue{
        private String id;
        private String name = "kevin";
        private int age = 32;
        private String createTime = "2019-12-19 07:52:26";
        private String updateTime="2019-12-19 07:52:26";
        private String description = "Hello World From SMaRT";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
