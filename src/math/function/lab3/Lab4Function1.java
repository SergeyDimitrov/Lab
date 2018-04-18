package math.function.lab3;

import math.function.Function;

public class Lab4Function1 extends Function {
    @Override
    public double getValue(double... x) {
        double fourX = x[0] * 4;
        double xPowThree = Math.pow(x[0], 3);
        return Math.cos(fourX) / x[0] / (xPowThree + 5);
    }

    @Override
    public int getVarCount() {
        return 1;
    }
}
