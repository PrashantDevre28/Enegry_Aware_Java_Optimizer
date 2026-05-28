package Optimizer.profiler;

public class RuntimeProfiler {

    public double getCpuUsage() {

        // Simulated CPU usage
        return Math.random() * 100;
    }

    public long getMemoryUsage() {

        Runtime runtime = Runtime.getRuntime();

        return (runtime.totalMemory()
                - runtime.freeMemory())
                / (1024 * 1024);
    }
}