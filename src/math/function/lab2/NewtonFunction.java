package math.function.lab2;

import math.function.Function;

public class NewtonFunction extends Function {
    private double l, r;

    public NewtonFunction(double l, double r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public double getValue(double... x) {
        Function function = new Lab3FunctionDer0();
        double ans = 0;
        int N = 3;
        double xs[] = new double[] {l, l + (r - l) / 3, l + (r - l) * 2 / 3, r};
        for (int i = 0; i <= N; i++) {
            ans += getF(function, xs, 0, i) * getW(x[0], xs,i - 1);
        }
        return ans;
    }

    private double getF(Function f, double[] xs, int leftBound, int rightBound) {
        if (leftBound == rightBound) {
            return f.getValue(xs[leftBound]);
        } else {
            return (getF(f, xs, leftBound + 1, rightBound) - getF(f, xs, leftBound, rightBound - 1)) / (xs[rightBound] - xs[leftBound]);
        }
    }

    private double getW(double x, double[] xs, int k) {
        if (k == -1) {
            return 1;
        }
        return getW(x, xs, k - 1) * (x - xs[k]);
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
