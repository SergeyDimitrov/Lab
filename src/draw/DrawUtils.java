package draw;

import math.function.Function;

public class DrawUtils {
    public static void draw(double l, double r, double step, Function... functions) {
        XYPlot plot = new XYPlot("Lab", l, r, step, functions);
        plot.pack();
        plot.setVisible(true);
    }
}
