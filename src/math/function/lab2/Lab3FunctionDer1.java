package math.function.lab2;

import math.function.Function;

public class Lab3FunctionDer1 extends Function{
    @Override
    public double getValue(double... x) {
        return Math.PI * 40 / Math.pow(20 + x[0], 2);
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
