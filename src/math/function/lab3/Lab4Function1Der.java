package math.function.lab3;

import math.function.Function;

public class Lab4Function1Der extends Function {
    @Override
    public double getValue(double... x) {
        double fourX = x[0] * 4;
        double xPowThree = Math.pow(x[0], 3);
        return 24 * x[0] * Math.sin(fourX) / Math.pow(xPowThree + 5, 2)
                + 18 * xPowThree * Math.cos(fourX) / Math.pow(xPowThree + 5, 3)
                - 16 * Math.cos(fourX) / (x[0] * (xPowThree + 5))
                + 2 * Math.cos(fourX) / (xPowThree * (xPowThree + 5))
                + 8 * Math.sin(fourX) / (Math.pow(x[0], 2) * (xPowThree + 5));
    }

    @Override
    public int getVarCount() {
        return 1;
    }
}
