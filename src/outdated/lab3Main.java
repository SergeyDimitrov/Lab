package outdated;

import draw.DrawUtils;
import math.function.lab3.Lab4Function1;
import math.function.lab3.Lab4Function1Der;
import math.function.lab3.Lab5Function1;
import math.utils.MathUtils;

public class lab3Main {
    public static void main(String[] args) {
//        DrawUtils.draw(1, 3, 1e-4, new Lab4Function1Der());
//        DrawUtils.draw(1, 2, 1e-4, new Lab4Function1());
        double h = MathUtils.getTrapezoidalH(new Lab4Function1Der().getValue(2), 1e-5, 1, 2);
        System.out.println("Шаг для метода трапеций: " + h);
        double trapezoidalIntegral = MathUtils.getTrapezoidalIntegral(new Lab4Function1(), 1, 2, h);
        System.out.println("Интеграл методом трапеций: " + trapezoidalIntegral);
        double simpsonIntegral = MathUtils.getSimpsonIntegral(new Lab4Function1(), 1, 2, 10);
        System.out.println("Интеграл методом Симпсона: " + simpsonIntegral);
        DrawUtils.draw(0, 0.3, 0.1, MathUtils.rungeKuttaMethod3(new Lab5Function1(), 0, 0, 0.1, 4),
                MathUtils.rungeKuttaMethod4(new Lab5Function1(), 0, 0, 0.1, 4),
                MathUtils.predictorCorrectorMethod(new Lab5Function1(), 0, 0, 0.1, 4));
    }
}
