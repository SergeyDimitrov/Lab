package math.function.lab2;

import math.function.Function;

public class Lab3FunctionDer2 extends Function {
    @Override
    public double getValue(double... x) {
        return -Math.PI * 80 / Math.pow(x[0] + 20, 3);
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
