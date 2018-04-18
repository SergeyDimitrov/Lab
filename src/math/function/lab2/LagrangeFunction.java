package math.function.lab2;

import math.function.Function;

public class LagrangeFunction extends Function {
    private double l, r;
    public LagrangeFunction(double l, double r) {
        this.l = l;
        this.r = r;
    }
    @Override
    public double getValue(double... x) {
        int N = 3;
        Function y = new Lab3FunctionDer0();
        double ans = 0;
        double xs[] = new double[] {l, l + (r - l) / 3, l + (r - l) * 2 / 3, r};
        for (int j = 0; j <= N; j++) {
            double add = 1;
            for (int k = 0; k <= N; k++) {
                if (k == j) {
                    continue;
                }
                add *= (x[0] - xs[k]) / (xs[j] - xs[k]);
            }
            ans += add * y.getValue(xs[j]);
        }
        return ans;
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
