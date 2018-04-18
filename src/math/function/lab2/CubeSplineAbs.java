package math.function.lab2;

import math.function.Function;

public class CubeSplineAbs extends CubeSpline {
    public CubeSplineAbs(double l, double r) {
        super(l, r);
    }

    @Override
    public double getValue(double... x) {
        double ans = super.getValue(x);
        Function f = new Lab3FunctionDer0();
        return Math.abs(ans - f.getValue(x[0])) / ans;
    }
}
