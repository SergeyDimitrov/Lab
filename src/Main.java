import draw.DrawUtils;
import math.function.Function;
import math.linear.ComplexNumber;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

/**
 * Created by pb on 19.04.2018.
 */
public class Main {

    private static final int N = 128; // Number of points
    private static final double a = 5;
    private static final double ahx = 2 * a / N;

    private static final double b = N / (4 * a);

    private static final double bhx = 2 * b / N;

    private static final int STEPS = 1000;

    private static ComplexNumber f(double x) {
        return ComplexNumber.add(ComplexNumber.exp(new ComplexNumber(0, 2 * PI * x)),
                    ComplexNumber.exp(new ComplexNumber(0, -5 * PI * x)));
    }

    private static ComplexNumber F(double w) {
        ComplexNumber tmp = ComplexNumber.exp(new ComplexNumber(0, 2 * PI * (1 - w) * a));
        ComplexNumber tmp2 = ComplexNumber.exp(new ComplexNumber(0, -2 * PI * (1 - w) * a));
        tmp.subtract(tmp2);
        ComplexNumber tmp3 = ComplexNumber.exp(new ComplexNumber(0, 2 * PI * (-2.5 - w) * a));
        ComplexNumber tmp4 = ComplexNumber.exp(new ComplexNumber(0, -2 * PI * (-2.5 - w) * a));
        tmp3.subtract(tmp4);
        tmp.divide(new ComplexNumber(1 - w, 0));
        tmp3.divide(new ComplexNumber(-2.5 - w, 0));
        tmp.add(tmp3);
        tmp.divide(new ComplexNumber(0, 2 * PI));
        return tmp;
    }

    private static ComplexNumber exp(ComplexNumber x) {
            return ComplexNumber.exp(x);
        }

    private static ComplexNumber exp(double x) {
            return exp(new ComplexNumber(x, 0));
        }

//    private static ComplexNumber f(double x) {
//        return exp(-x * x);
//    }

    public static void swap(ComplexNumber[] a) {
        int N = a.length;
        for (int i = 0; i < (N >> 1); i++) {
            ComplexNumber tmp = a[i];
            a[i] = a[i + (N >> 1)];
            a[i + (N >> 1)] = tmp;
        }
    }

    public static ComplexNumber[] FT(ComplexNumber[] f) {
        if (f.length != N) {
            throw new IllegalArgumentException();
        }
        if (Integer.bitCount(f.length) != 1) {
            throw new IllegalArgumentException();
        }

        ComplexNumber[] F = new ComplexNumber[f.length];
        for (int i = 0; i < N; i++) {
            F[i] = new ComplexNumber();
            for (int j = 0; j < N; j++) {
                F[i] = ComplexNumber.add(F[i],
                        ComplexNumber.multiply(f[j], ComplexNumber.exp(new ComplexNumber(0, -2 * PI * i * j / N))));
            }
        }
        return F;
    }

    public static ComplexNumber[] opticalFiniteFT() {
        ComplexNumber[] f = new ComplexNumber[N];

        for (int i = 0; i < N; i++) {
            f[i] = f(-a + i * ahx);
        }

        swap(f);

        ComplexNumber[] F = FT(f);

        for (int i = 0; i < N; i++) {
            F[i] = ComplexNumber.multiply(F[i], new ComplexNumber(ahx, 0));
        }

        swap(F);

        return F;
    }

    private static double sqr(double x) {
        return x * x;
    }

    private static ComplexNumber[] integralFT() {
        ComplexNumber[] F = new ComplexNumber[N];
        for (int i = 0; i < N; i++) {
            double u = -b + bhx * i;
            double dx = 2 * a / STEPS;
            F[i] = new ComplexNumber();
            for (int j = 0; j < STEPS; j++) {
                double x = -a + j * dx;
                F[i] = ComplexNumber.add(F[i],
                        ComplexNumber.multiply(f(x), exp(new ComplexNumber(0, -2 * PI * u * x))));
            }
            F[i] = ComplexNumber.multiply(F[i], new ComplexNumber(dx, 0));
        }
        return F;
    }

    public static void main(String[] args) {

        ComplexNumber[] res = opticalFiniteFT();
        ComplexNumber[] res2 = integralFT();

//        for (int i = 0; i < res2.length; i++) {
//            res[i].divide(new ComplexNumber(sqrt(PI), 0));
//            res2[i].divide(new ComplexNumber(sqrt(PI), 0));
//        }

        Function f1 = new Function() {
            @Override
            public double getValue(double... x) {
                for (int i = 0; i < N; i++) {
                    double c = -b + i * bhx;
                    if (c > x[0]) {
                        double p = c - bhx;
                        return (res[i - 1].getRe() * (c - x[0]) + res[i].getRe() * (x[0] - p)) / bhx;
                    }
                }
                return res[N - 1].getRe();
            }

            @Override
            public int getVarCount() {
                return 1;
            }
        };

        Function f2 = new Function() {
            @Override
            public double getValue(double... x) {
                for (int i = 0; i < N; i++) {
                    double c = -b + i * bhx;
                    if (c > x[0]) {
                        double p = c - bhx;
                        return (res2[i - 1].getRe() * (c - x[0]) + res2[i].getRe() * (x[0] - p)) / bhx;
                    }
                }
                return res2[N - 1].getRe();
            }

            @Override
            public int getVarCount() {
                return 1;
            }
        };

//        Function f3 = new Function() {
//            @Override
//            public double getValue(double... x) {
//                for (int i = 0; i < N; i++) {
//                    double c = -b + i * bhx;
//                    if (c > x[0]) {
//                        double p = c - bhx;
//                        return (f(p * PI).getRe() * (c - x[0]) + f(c * PI).getRe() * (x[0] - p)) / bhx;
//                    }
//                }
//                return f((b - bhx) * PI).getRe();
//            }
//
//            @Override
//            public int getVarCount() {
//                return 1;
//            }
//        };


        Function f3 = new Function() {
            @Override
            public double getValue(double... x) {
                for (int i = 0; i < N; i++) {
                    double c = -b + i * bhx;
                    if (c > x[0]) {
                        double p = c - bhx;
                        return (F(p).getRe() * (c - x[0]) + F(c).getRe() * (x[0] - p)) / bhx;
                    }
                }
                return F(b - bhx).getRe();
            }

            @Override
            public int getVarCount() {
                return 1;
            }
        };

        DrawUtils.draw(-b, b - bhx, 1e-3, f1, f2, f3);

//        for (int i = 0; i < N; i++) {
//            System.out.println((-b + i * bhx) + " " + res[i] + " " + res2[i] + " " + f((-b + i * bhx) * PI));
//        }

        for (int i = 0; i < N; i++) {
            System.out.println((-b + i * bhx) + " " + res[i] + " " + res2[i] + " " + F(-b + i * bhx));
        }
    }

}
