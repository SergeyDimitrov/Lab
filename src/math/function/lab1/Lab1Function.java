package math.function.lab1;

import math.function.Function;

public class Lab1Function extends Function {
    @Override
    public double getValue(double... x) {
        if (x.length != getVarCount()) {
            throw new IllegalArgumentException();
        }
        return Math.pow(x[0], 3) - 0.8 * Math.pow(x[0], 2) - 6.8 * x[0] + 0.7;
    }

    @Override
    public double getDerivative(int var, double... x) {
        if (x.length != getVarCount()) {
            throw new IllegalArgumentException();
        }
        switch (var) {
            case 1:
                return 3 * Math.pow(x[0], 2) - 1.6 * x[0] - 6.8;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public int getVarCount() {
        return 1;
    }

    @Override
    public String toString() {
        return "x ^ 3 - 0.8 * x ^ 2 - 6.8 * x + 0.7";
    }
}
