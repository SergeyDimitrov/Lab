package math.function.lab2;

import math.function.Function;

public class Lab3FunctionDer0 extends Function {
    @Override

    public double getValue(double... x) {
        return Math.PI * x[0] / (10 + x[0] / 2);
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
