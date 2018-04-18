package math.function.lab2;

import math.function.Function;
import math.linear.Matrix;
import math.linear.MatrixUtils;
import math.linear.Vector;

public class CubeSpline extends Function {

    private double l, r;

    public CubeSpline(double l, double r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public double getValue(double... x) {
        int N = 3;
        Function f = new Lab3FunctionDer0();
        double xs[] = new double[] {l, l + (r - l) / 3, l + (r - l) * 2 / 3, r};
        Matrix A = new Matrix(N + 1, N + 1);
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= i + 1; j++) {
                A.setValue(Math.pow(xs[i + 1] - xs[j - 1], 3), i, j);
            }
        }
        for (int i = 0; i < N; i++) {
            A.setValue(xs[i + 1] - xs[0], i, 0);
        }

        for (int i = 1; i <= N; i++) {
            A.setValue(6 * (xs[3] - xs[i - 1]), N, i);
        }

        Matrix invA = MatrixUtils.inverseMatrix(A);
        Vector b = new Vector(N + 1);
        for (int i = 0; i < N; i++) {
            b.setValue(f.getValue(xs[i + 1]) - f.getValue(xs[0]), i, 0);
        }
        Matrix C = MatrixUtils.multiply(invA, b);
        double ans = f.getValue(xs[0]) + C.getValue(0, 0) * (x[0] - xs[0])
                + C.getValue(1, 0) * Math.pow(x[0] - xs[0], 3);
        if (x[0] >= xs[1]) {
            ans += C.getValue(2, 0) * Math.pow(x[0] - xs[1], 3);
        }
        if (x[0] >= xs[2]) {
            ans += C.getValue(3, 0) * Math.pow(x[0] - xs[2], 3);
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
