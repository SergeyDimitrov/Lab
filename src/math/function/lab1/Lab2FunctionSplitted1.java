package math.function.lab1;

import math.function.Function;

public class Lab2FunctionSplitted1 extends Function {

    @Override
    public double getValue(double... x) {
        if (x.length != getVarCount()) {
            throw new IllegalArgumentException();
        }
        return Math.cos(x[0] + 0.5) - 2;
    }

    @Override
    public double getDerivative(int var, double... x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getVarCount() {
        return 1;
    }
}
