package Optimizer.profiler;

public class Metrics {

    private int methods;
    private int methodCalls;
    private int objectCreations;
    private int loops;
    private int arithmeticOperations;

    public void incrementMethods() {
        methods++;
    }

    public void incrementMethodCalls() {
        methodCalls++;
    }

    public void incrementObjectCreations() {
        objectCreations++;
    }

    public void incrementLoops() {
        loops++;
    }

    public void incrementArithmeticOperations() {
        arithmeticOperations++;
    }

    public int getMethods() {
        return methods;
    }

    public int getMethodCalls() {
        return methodCalls;
    }

    public int getObjectCreations() {
        return objectCreations;
    }

    public int getLoops() {
        return loops;
    }

    public int getArithmeticOperations() {
        return arithmeticOperations;
    }
}