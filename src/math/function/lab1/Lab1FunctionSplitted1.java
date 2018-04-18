package math.function.lab1;

import math.function.Function;

public class Lab1FunctionSplitted1 extends Function {

    @Override
    public double getValue(double... x) {
        if (x.length != getVarCount()) {
            throw new IllegalArgumentException();
        }
        return Math.pow(x[0], 3);
    }

    @Override
    public double getDerivative(int var, double... x) {
        if (x.length != getVarCount()) {
            throw new IllegalArgumentException();
        }
        switch (var) {
            case 1:
                return 3 * Math.pow(x[0], 2);
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
        return "x ^ 3";
    }
}
