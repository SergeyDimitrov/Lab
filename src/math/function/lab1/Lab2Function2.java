package math.function.lab1;

import math.function.Function;

public class Lab2Function2 extends Function {
    @Override
    public double getValue(double... x) {
        if (x.length != getVarCount()) {
            throw new IllegalArgumentException();
        }
        return Math.sin(x[1]) - 2 * x[0] - 1;
    }

    @Override
    public double getDerivative(int var, double... x) {
        if (x.length != getVarCount()) {
            throw new IllegalArgumentException();
        }
        switch (var) {
            case 1:
                return -2;
            case 2:
                return Math.cos(x[1]);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public int getVarCount() {
        return 2;
    }

    @Override
    public String toString() {
        return "sin(y) - 2 * x - 1";
    }
}
