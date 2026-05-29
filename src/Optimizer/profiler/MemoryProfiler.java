package Optimizer.profiler;

public class MemoryProfiler {

    private long usedMemory;
    private long freeMemory;
    private long totalMemory;
    private long maxMemory;

    public void analyzeMemory() {

        Runtime runtime =
                Runtime.getRuntime();

        totalMemory =
                runtime.totalMemory();

        freeMemory =
                runtime.freeMemory();

        usedMemory =
                totalMemory - freeMemory;

        maxMemory =
                runtime.maxMemory();
    }

    public long getUsedMemoryMB() {

        return usedMemory
                / (1024 * 1024);
    }

    public long getFreeMemoryMB() {

        return freeMemory
                / (1024 * 1024);
    }

    public long getTotalMemoryMB() {

        return totalMemory
                / (1024 * 1024);
    }

    public long getMaxMemoryMB() {

        return maxMemory
                / (1024 * 1024);
    }
}