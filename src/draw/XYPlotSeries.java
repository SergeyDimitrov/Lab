package draw;

import math.function.Function;
import org.jfree.data.xy.XYSeries;

public class XYPlotSeries extends XYSeries {

    public XYPlotSeries(Comparable key) {
        super(key, true);
    }

    public XYPlotSeries(Comparable key, Function function, double l, double r, double step) {
        this(key);

        while (l < r) {
            if (function.isInverse()) {
                add(function.getValue(l), l);
            } else {
                add(l, function.getValue(l));
            }
            l += step;
        }
        if (function.isInverse()) {
            add(function.getValue(r), r);
        } else {
            add(r, function.getValue(r));
        }
    }
}
