package draw;

import com.sun.glass.ui.Size;
import math.function.Function;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;

public class XYPlot extends ApplicationFrame {

    private Color[] goodColors = new Color[] {Color.BLACK, Color.BLUE, Color.RED};

    public XYPlot(String title, double l, double r, double step, Function... functions) {
        super(title);
        XYSeriesCollection datasetCollection = new XYSeriesCollection();
        for (Function function : functions) {
            XYSeries dataset = new XYPlotSeries(function.toString(), function, l, r, step);
            datasetCollection.addSeries(dataset);
        }
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "X",
                "Y",
                datasetCollection,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
//        org.jfree.chart.plot.XYPlot plot = (org.jfree.chart.plot.XYPlot) chart.getPlot();
//        for (int i = 0; i < functions.length; i++) {
//            plot.getRenderer().setSeriesPaint(i, goodColors[i]);
//        }
        ChartPanel panel = new ChartPanel(chart);
        Size screenSize = ScreenUtils.getScreenSize();
        panel.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        setContentPane(panel);
    }
}
