package math.function.lab1;

import math.function.Function;

public class Lab2FunctionSplitted2 extends Function {
    @Override
    public double getValue(double... x) {
        if (x.length != getVarCount()) {
            throw new IllegalArgumentException();
        }
        return (Math.sin(x[0]) - 1) / 2;
    }

    @Override
    public double getDerivative(int var, double... x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getVarCount() {
        return 1;
    }

    @Override
    public boolean isInverse() {
        return true;
    }
}
