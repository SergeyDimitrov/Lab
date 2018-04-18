package math.function.lab2;

import math.function.Function;

public class NewtonFunctionAbs extends NewtonFunction {

    public NewtonFunctionAbs(double l, double r) {
        super(l, r);
    }

    @Override
    public double getValue(double... x) {
        double ans = super.getValue(x);
        Function y = new Lab3FunctionDer0();
        return Math.abs(ans - y.getValue(x[0])) / ans;
    }
}
