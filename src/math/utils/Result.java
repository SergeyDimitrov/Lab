package math.utils;

import java.util.Arrays;

public class Result {

    public final double[] x;
    public final double[] y;
    public final int iterationsCount;
    public String methodName;
    public double norm;

    public Result(double[] x, double[] y, int iterationsCount, String methodName) {
        this.x = x;
        this.y = y;
        this.iterationsCount = iterationsCount;
        this.methodName = methodName;

    }

    public Result(double[] x, double[] y, int iterationsCount, String methodName, double norm) {
        this(x, y, iterationsCount, methodName);
        this.norm = norm;
    }

    @Override
    public String toString() {
        return  methodName + "\nX: " + Arrays.toString(x) + "\nY: " + Arrays.toString(y) + "\nNorm: " + norm + "\nIterations count: " + iterationsCount;
    }
}
