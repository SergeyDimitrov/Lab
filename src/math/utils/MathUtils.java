package math.utils;

import math.function.Function;
import math.function.lab3.Lab5Function1;
import math.linear.Matrix;
import math.linear.MatrixUtils;
import math.linear.NormUtils;

import java.util.Arrays;

public class MathUtils {
    public static Result binarySearchMethod(Function function, double l, double r, double rangeEps, double valEps) {
        double leftValue = function.getValue(l);
        double rightValue = function.getValue(r);
        int iterationsCount = 0;
        while (r - l > rangeEps) {
            iterationsCount++;
            double m = (r + l) / 2;
            double middleValue = function.getValue(m);
            if (Math.abs(middleValue) < valEps) {
                return new Result(new double[] {m}, new double[] {middleValue}, iterationsCount, "binarySearchMethod: ");
            } else if (middleValue * leftValue < 0) {
                r = m;
                rightValue = middleValue;
            } else if (middleValue * rightValue < 0) {
                l = m;
                leftValue = middleValue;
            }
        }
        return new Result(new double[] {l}, new double[] {leftValue}, iterationsCount, "binarySearchMethod: ");
    }

    public static Result hybridNewtonMethod(Function function, double initX, double rangeEps) {
        double prevX;
        double nextX = initX;
        int iterationsCount = 0;
        do {
            prevX = nextX;
            nextX = prevX - function.getValue(prevX) / function.getDerivative(1, prevX);
            iterationsCount++;
            while (Math.abs(nextX - prevX) >= rangeEps && Math.abs(function.getValue(nextX)) >= Math.abs(function.getValue(prevX))) {
                nextX = (nextX + prevX) / 2;
                iterationsCount++;
            }
        } while (Math.abs(nextX - prevX) >= rangeEps);
        return new Result(new double[] {nextX}, new double[] {function.getValue(nextX)}, iterationsCount, "hybridNewtonMethod");
    }

    public static Result modifiedNewtonMethod(Function function1, Function function2, double[] x, double rangeEps) {
        Matrix jacobian = buildJacobian(x, function1, function2);
        Matrix inverseJacobian = MatrixUtils.inverseMatrix(jacobian);
        double[] prevX;
        double[] nextX = Arrays.copyOf(x, x.length);
        int iterationCount = 0;
        do {
            prevX = Arrays.copyOf(nextX, nextX.length);
            Matrix delta = MatrixUtils.multiply(inverseJacobian, buildVectorFunction(prevX, function1, function2));
            for (int i = 0; i < x.length; i++) {
                nextX[i] = prevX[i] - delta.getValue(i, 0);
            }
            iterationCount++;
        } while (NormUtils.getNormInf(prevX, nextX) > rangeEps);
        return new Result(nextX, new double[] {function1.getValue(nextX), function2.getValue(nextX)},
                iterationCount, "modifiedNewtonMethod", NormUtils.getNormInf(new double[] {function1.getValue(nextX), function2.getValue(nextX)}));
    }

    public static Result newtonMethod(Function function1, Function function2, double[] x, double rangeEps) {
        double[] prevX;
        double[] nextX = Arrays.copyOf(x, x.length);
        int iterationCount = 0;
        do {
            prevX = Arrays.copyOf(nextX, nextX.length);
            Matrix jacobian = buildJacobian(prevX, function1, function2);
            Matrix inverseJacobian = MatrixUtils.inverseMatrix(jacobian);
            Matrix delta = MatrixUtils.multiply(inverseJacobian, buildVectorFunction(prevX, function1, function2));
            for (int i = 0; i < x.length; i++) {
                nextX[i] = prevX[i] - delta.getValue(i, 0);
            }
            iterationCount++;
        } while (NormUtils.getNormInf(prevX, nextX) > rangeEps);
        return new Result(nextX, new double[] {function1.getValue(nextX), function2.getValue(nextX)},
                iterationCount, "newtonMethod", NormUtils.getNormInf(new double[] {function1.getValue(nextX), function2.getValue(nextX)}));
    }

    public static Matrix buildJacobian(double[] x, Function... functions) {
        Matrix jacobian = new Matrix(functions.length, x.length);
        for (int i = 0; i < jacobian.getRowsCount(); i++) {
            for (int j = 0; j < jacobian.getColumnsCount(); j++) {
                jacobian.setValue(functions[i].getDerivative(j + 1, x), i, j);
            }
        }
        return jacobian;
    }

    public static Matrix buildVectorFunction(double[] x, Function... functions) {
        Matrix vectorFunction = new Matrix(functions.length, 1);
        for (int i = 0; i < functions.length; i++) {
            vectorFunction.setValue(functions[i].getValue(x), i, 0);
        }
        return vectorFunction;
    }

    public static double getTrapezoidalH(double M2, double eps, double a, double b) {
        return Math.sqrt(12 * eps / M2 / (b - a));
    }

    public static double getTrapezoidalIntegral(Function function, double l, double r, double h) {
        double sum = 0;
        for (double currX = l + h; currX < r; currX += h) {
            sum += function.getValue(currX);
        }
        return h * ((function.getValue(l) + function.getValue(r)) / 2 + sum);
    }

    public static double getSimpsonIntegral(Function function, double l, double r, int n) {
        double sum = 0;
        double h = (r - l) / n;
        double prevX = 0;
        for (int i = 0; i <= n; i++) {
            double currX = l + h * i;
            double currVal = function.getValue(currX);
            if (i == 0 || i == n) {
                sum += currVal;
            } else {
                sum += 2 * currVal;
            }
            if (i != 0) {
                sum += 4 * function.getValue((prevX + currX) / 2);
            }
            prevX = currX;
        }
        return h / 6 * sum;
    }

    public static Function rungeKuttaMethod3(Function function, double x0, double y0, double h, int n) {
        double[] functionValues = new double[n];
        functionValues[0] = y0;
        for (int i = 1; i < n; i++) {
            double x = x0 + (i - 1) * h;
            double y = functionValues[i - 1];
            double phi0 = h * function.getValue(x, y);
            double phi1 = h * function.getValue(x + h / 2, y + phi0 / 2);
            double phi2 = h * function.getValue(x + h, y - phi0 + 2 * phi1);
            double dy = (phi0 + 4 * phi1 + phi2) / 6;
            functionValues[i] = functionValues[i - 1] + dy;
        }
        return new Function() {
            @Override
            public double getValue(double... x) {
                for (int i = 0; i < n; i++) {
                    if (Math.abs(x0 + i * h - x[0]) < 1e-12) {
                        return functionValues[i];
                    }
                }
                return 0;
            }

            @Override
            public int getVarCount() {
                return 1;
            }

            @Override
            public String toString() {
                return "Метод Рунге-Кутты 3";
            }
        };
    }

    public static Function rungeKuttaMethod4(Function function, double x0, double y0, double h, int n) {
        double[] functionValues = new double[n];
        functionValues[0] = y0;
        for (int i = 1; i < n; i++) {
            double x = x0 + (i - 1) * h;
            double y = functionValues[i - 1];
            double phi0 = h * function.getValue(x, y);
            double phi1 = h * function.getValue(x + h / 2, y + phi0 / 2);
            double phi2 = h * function.getValue(x + h / 2, y + phi1 / 2);
            double phi3 = h * function.getValue(x + h, y + phi2);
            double dy = (phi0 + 2 * phi1 + 2 * phi2 + phi3) / 6;
            functionValues[i] = functionValues[i - 1] + dy;
        }
        return new Function() {
            @Override
            public double getValue(double... x) {
                for (int i = 0; i < n; i++) {
                    if (Math.abs(x0 + i * h - x[0]) < 1e-12) {
                        return functionValues[i];
                    }
                }
                return 0;
            }

            @Override
            public int getVarCount() {
                return 1;
            }

            @Override
            public String toString() {
                return "Метод Рунге-Кутты 4";
            }
        };
    }

    public static Function predictorCorrectorMethod(Function function, double x0, double y0, double h, int n) {
        double[] functionValues = new double[n];
        functionValues[0] = y0;
        for (int i = 1; i < n; i++) {
            double x = x0 + h * (i - 1);
            double y = functionValues[i - 1];
            double predictor = y + Math.pow(h, 2) * function.getValue(x, y);
            double dy = h * function.getValue(x, y) + (function.getValue(x + Math.pow(h, 2), predictor) - function.getValue(x, y)) / 2;
            functionValues[i] = y + dy;
        }
        return new Function() {
            @Override
            public double getValue(double... x) {
                for (int i = 0; i < n; i++) {
                    if (Math.abs(x0 + i * h - x[0]) < 1e-12) {
                        return functionValues[i];
                    }
                }
                return 0;
            }

            @Override
            public int getVarCount() {
                return 1;
            }

            @Override
            public String toString() {
                return "Метод прогноза и коррекции";
            }
        };
    }
}
