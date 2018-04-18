package math.function.lab1;

import math.function.Function;

public class Lab2Function1 extends Function {

    @Override
    public double getValue(double... x) {
        if (x.length != getVarCount()) {
            throw new IllegalArgumentException();
        }
        return Math.cos(x[0] + 0.5) - x[1] - 2;
    }

    @Override
    public double getDerivative(int var, double... x) {
        if (x.length != getVarCount()) {
            throw new IllegalArgumentException();
        }
        switch (var) {
            case 1:
                return -Math.sin(x[0] + 0.5);
            case 2:
                return -1;
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
        return "cos(x + 0.5) - y - 2";
    }
}
