import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Graph {
    private JFreeChart chart;
    private JFreeChart chart1;
    private JPanel jPanel;
    private JPanel jPanel1;
    private XYSeriesCollection xyDataset;
    private XYSeriesCollection xyDataset1;

    final private Shape shape = new Ellipse2D.Double(0 , 0, 2, 2);
    final private Shape shape2 = new Ellipse2D.Double(0 , 0, 7, 7);
    final private float stroke = 1.2f;
    final private double resultEquation1 = 1.5;
    final private double resultEquation2X = 0.3971886932;
    final private double resultEquation2Y = 0.24883787704;

    //Equation 1
    public Graph(Equation1 computing){
        this.createDataset(computing);
        this.createChart(computing);
        this.createChart();

        jPanel = new JPanel();
        jPanel.add(new ChartPanel(chart));
        jPanel1 = new JPanel();
        jPanel1.add(new ChartPanel(chart1));
    }

    private void createDataset(Equation1 computing) {
        XYSeries xySeries = new XYSeries(0);
        for (int i = 0; i < computing.getI(); i++)
            xySeries.add(computing.getTi(i), computing.get_yi(i));
        xyDataset = new XYSeriesCollection();
        xyDataset.addSeries(xySeries);

        XYSeries xySeries1 = new XYSeries(1);
        xySeries1.add(0, resultEquation1);
        xySeries1.add(computing.getTi(computing.getI()) + 1, resultEquation1);
        xyDataset.addSeries(xySeries1);

        XYSeries xySeries2 = new XYSeries(2);
        for (double i = -10; i < 10; i+=0.1){
            xySeries2.add(i, computing.getFuncBx(i));
        }
        xyDataset1 = new XYSeriesCollection();
        xyDataset1.addSeries(xySeries2);

        XYSeries xySeries3 = new XYSeries(3);
        xySeries3.add(-10, 0);
        xySeries3.add(10, 0);
        xyDataset1.addSeries(xySeries3);

        XYSeries xySeries4 = new XYSeries(4);
        xySeries4.add(0, -1);
        xySeries4.add(0, 6);
        xyDataset1.addSeries(xySeries4);
    }

    private void createChart(Equation1 computing) {
        // OY
        NumberAxis yAxis = new NumberAxis("y");
        yAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        yAxis.setTickUnit(new NumberTickUnit(0.1));
        yAxis.setPositiveArrowVisible(true);

        // OX
        NumberAxis xAxis = new NumberAxis("t");
        xAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        xAxis.setTickUnit(new NumberTickUnit(2));
        xAxis.setPositiveArrowVisible(true);

        //Renderer
        XYItemRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesStroke(0, new BasicStroke(stroke));
        renderer.setSeriesPaint(0, Color.BLUE);
        ((XYLineAndShapeRenderer) renderer).setSeriesShapesVisible(0, false);
        renderer.setSeriesStroke(1, new BasicStroke(stroke));
        renderer.setSeriesPaint(1, Color.RED);
        ((XYLineAndShapeRenderer) renderer).setSeriesShapesVisible(1, false);
        renderer.setSeriesStroke(2, new BasicStroke(stroke));
        renderer.setSeriesPaint(2, Color.GREEN);
        ((XYLineAndShapeRenderer) renderer).setSeriesShapesVisible(2, false);

        //Plot
        XYPlot plot = new XYPlot(xyDataset, xAxis, yAxis, renderer);

        //Chart
        chart = new JFreeChart(plot);
        chart.removeLegend();
    }

    private void createChart() {
        // OY
        NumberAxis yAxis = new NumberAxis("Bx");
        yAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        yAxis.setTickUnit(new NumberTickUnit(1));
        yAxis.setPositiveArrowVisible(true);
        yAxis.setRange(new Range(-1, 6));

        // OX
        NumberAxis xAxis = new NumberAxis("x");
        xAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        xAxis.setTickUnit(new NumberTickUnit(2));
        xAxis.setPositiveArrowVisible(true);
        xAxis.setRange(new Range(-10, 10));

        //Renderer
        XYItemRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesStroke(0, new BasicStroke(stroke));
        renderer.setSeriesPaint(0, Color.BLUE);
        ((XYLineAndShapeRenderer) renderer).setSeriesShapesVisible(0, false);

        renderer.setSeriesStroke(1, new BasicStroke(stroke));
        renderer.setSeriesPaint(1, Color.RED);
        ((XYLineAndShapeRenderer) renderer).setSeriesShapesVisible(1, false);

        renderer.setSeriesStroke(2, new BasicStroke(stroke));
        renderer.setSeriesPaint(2, Color.RED);
        ((XYLineAndShapeRenderer) renderer).setSeriesShapesVisible(2, false);

        //Plot
        XYPlot plot = new XYPlot(xyDataset1, xAxis, yAxis, renderer);

        //Chart
        chart1 = new JFreeChart(plot);
        chart1.removeLegend();
    }


    //Equation 2
    public Graph(Equation2 computing){
        this.createDataset(computing);
        this.createChart(computing);

        jPanel = new JPanel();
        jPanel.add(new ChartPanel(chart));
    }

    private void createDataset(Equation2 computing) {
        XYSeries xySeries = new XYSeries(0);
        for (int i = 0; i < computing.get_ySize(); i++){
            xySeries.add(computing.get_y1i(i), computing.get_y2i(i));
        }
        xyDataset = new XYSeriesCollection();
        xyDataset.addSeries(xySeries);

        XYSeries xySeries1 = new XYSeries(1);
        xySeries1.add(resultEquation2X, resultEquation2Y);
        xyDataset.addSeries(xySeries1);
    }

    private void createChart(Equation2 computing) {
        // OY
        NumberAxis yAxis = new NumberAxis("y2");
        yAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        yAxis.setTickUnit(new NumberTickUnit(0.1));
        yAxis.setPositiveArrowVisible(true);

        // OX
        NumberAxis xAxis = new NumberAxis("y1");
        xAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
        xAxis.setTickUnit(new NumberTickUnit(0.1));
        xAxis.setPositiveArrowVisible(true);

        //Renderer
        XYItemRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setBaseShape(shape);
        renderer.setSeriesShape(0, shape);
        renderer.setSeriesShape(1, shape2);
        renderer.setSeriesPaint(0, Color.BLUE);
        ((XYLineAndShapeRenderer) renderer).setSeriesLinesVisible(0, false);
        ((XYLineAndShapeRenderer) renderer).setSeriesShapesVisible(0, true);
        renderer.setSeriesPaint(1, Color.RED);
        ((XYLineAndShapeRenderer) renderer).setSeriesLinesVisible(1, false);
        ((XYLineAndShapeRenderer) renderer).setSeriesShapesVisible(1, true);

        //Plot
        XYPlot plot = new XYPlot(xyDataset, xAxis, yAxis, renderer);

        //Chart
        chart = new JFreeChart(plot);
        chart.removeLegend();
    }

    public JPanel getjPanel() {return jPanel;}
    public JPanel getjPanel1() {return jPanel1;}
}