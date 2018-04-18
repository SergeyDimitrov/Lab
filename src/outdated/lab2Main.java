package outdated;

import draw.DrawUtils;
import math.function.lab2.*;

public class lab2Main {
    public static void main(String[] args) {
        double l = 9;
        double r = 10;
        DrawUtils.draw(l, r, 1e-4,
                new Lab3FunctionDer0(),
                new LagrangeFunction(l, r),
                new NewtonFunction(l, r),
                new LinearSpline(l, r),
                new ParabolicSpline(l, r),
                new CubeSpline(l, r));

        DrawUtils.draw(l, r, 1e-4,
                new LagrangeFunctionAbs(l, r),
                new NewtonFunctionAbs(l, r),
                new LinearSplineAbs(l, r),
                new ParabolicSplineAbs(l, r),
                new CubeSplineAbs(l, r));
    }
}
