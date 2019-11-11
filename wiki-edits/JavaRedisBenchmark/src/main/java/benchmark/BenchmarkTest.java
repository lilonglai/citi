package benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkTest {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(JedisBenchmark.class.getSimpleName()).include(LettuceBenchmark.class.getSimpleName())
                .output("Throughput.log").forks(1).build();
        new Runner(options).run();
    }
}
