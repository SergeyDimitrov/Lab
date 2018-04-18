package math.function.lab1;

import math.function.Function;

public class Test1 extends Function {
    @Override
    public double getValue(double... x) {
        return Math.sin(x[0]);
    }

    @Override
    public double getDerivative(int var, double... x) {
        return 0;
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
