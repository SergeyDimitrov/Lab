package math.function.lab2;

import math.function.Function;
import math.linear.Matrix;
import math.linear.MatrixUtils;
import math.linear.Vector;

public class LinearSpline extends Function {

    private double l, r;

    public LinearSpline(double l, double r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public double getValue(double... x) {
        int N = 3;
        Function f = new Lab3FunctionDer0();
        double xs[] = new double[] {l, l + (r - l) / 3, l + (r - l) * 2 / 3, r};
        Matrix A = new Matrix(N, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                A.setValue(xs[i + 1] - xs[j], i, j);
            }
        }
        Matrix invA = MatrixUtils.inverseMatrix(A);
        Vector b = new Vector(N);
        for (int i = 0; i < N; i++) {
            b.setValue(f.getValue(xs[i + 1]) - f.getValue(xs[0]), i, 0);
        }
        Matrix C = MatrixUtils.multiply(invA, b);
        double ans = f.getValue(xs[0]) + C.getValue(0, 0) * (x[0] - xs[0]);
        if (x[0] >= xs[1]) {
            ans += C.getValue(1, 0) * (x[0] - xs[1]);
        }
        if (x[0] >= xs[2]) {
            ans += C.getValue(2, 0) * (x[0] - xs[2]);
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
