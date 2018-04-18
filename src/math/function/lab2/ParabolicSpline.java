package math.function.lab2;

import math.function.Function;
import math.linear.Matrix;
import math.linear.MatrixUtils;
import math.linear.Vector;

public class ParabolicSpline extends Function {

    private double l, r;

    public ParabolicSpline(double l, double r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public double getValue(double... x) {
        int N = 3;
        Function f = new Lab3FunctionDer0();
        double xs[] = new double[] {l, l + (r - l) / 3, l + (r - l) * 2 / 3, r};
        Matrix A = new Matrix(N + 1, N + 1);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                A.setValue(Math.pow(xs[i] - xs[j - 1], 2), i, j);
            }
        }
        for (int i = 0; i <= N; i++) {
            A.setValue(xs[i] - xs[0], i, 0);
        }
        A.setValue(2, 0, 1);

        Matrix invA = MatrixUtils.inverseMatrix(A);
        Vector b = new Vector(N + 1);
        for (int i = 0; i <= N; i++) {
            b.setValue(f.getValue(xs[i]) - f.getValue(xs[0]), i, 0);
        }
        Matrix C = MatrixUtils.multiply(invA, b);
        double ans = f.getValue(xs[0]) + C.getValue(0, 0) * (x[0] - xs[0])
                + C.getValue(1, 0) * Math.pow(x[0] - xs[0], 2);
        if (x[0] >= xs[1]) {
            ans += C.getValue(2, 0) * Math.pow(x[0] - xs[1], 2);
        }
        if (x[0] >= xs[2]) {
            ans += C.getValue(3, 0) * Math.pow(x[0] - xs[2], 2);
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
