package outdated;

import draw.DrawUtils;
import math.function.lab1.*;
import math.utils.MathUtils;

import java.util.Scanner;

public class lab1Main {
    public static void main(String[] args) {
        DrawUtils.draw(-4, 4, 1e-2, new Lab1FunctionSplitted1(), new Lab1FunctionSplitted2());
        double x, y;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите отрезок с корнем:");
        double l = sc.nextDouble();
        double r = sc.nextDouble();
        System.out.println(MathUtils.binarySearchMethod(new Lab1Function(), l, r, 1e-7, 1e-7));
        System.out.println("Введите начальное приближение:");
        x = sc.nextDouble();
        System.out.println(MathUtils.hybridNewtonMethod(new Lab1Function(), 0, 1e-12));
        DrawUtils.draw(-5, 5, 1e-2, new Lab2FunctionSplitted1(), new Lab2FunctionSplitted2());
        System.out.println("Введите начальное приближение для метода Ньютона: ");
        x = sc.nextDouble();
        y = sc.nextDouble();
        System.out.println(MathUtils.newtonMethod(new Lab2Function1(), new Lab2Function2(), new double[] {x, y}, 1e-5));
        System.out.println("Введите начальное приближение для модифицированного метода Ньютона: ");
        x = sc.nextDouble();
        y = sc.nextDouble();
        System.out.println(MathUtils.modifiedNewtonMethod(new Lab2Function1(), new Lab2Function2(), new double[] {x, y}, 1e-5));
    }
}
