package math.function.lab3;

import math.function.Function;

public class Lab5Function1 extends Function {
    @Override
    public double getValue(double... x) {
        return -(1 + x[0] * x[1]) + 0.7 * (x[0] + x[1]);
    }

    @Override
    public int getVarCount() {
        return 2;
    }
}
