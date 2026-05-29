package Optimizer.profiler;

public class ExecutionTimer {

    private long startTime;
    private long endTime;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public long getExecutionTimeMillis() {
        return (endTime - startTime) / 1_000_000;
    }

    public long getExecutionTimeNano() {
        return (endTime - startTime);
    }
}