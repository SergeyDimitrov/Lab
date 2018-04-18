package math.linear;

public class NormUtils {
    public static double getNormInf(double[] x) {
        double norm = 0;
        for (int i = 0; i < x.length; i++) {
            norm = Math.max(norm, Math.abs(x[i]));
        }
        return norm;
    }

    public static double getNormInf(double[] x, double[] y) {
        double norm = 0;
        for (int i = 0; i < x.length; i++) {
            norm = Math.max(norm, Math.abs(x[i] - y[i]));
        }
        return norm;
    }
}
