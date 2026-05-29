package Optimizer.graph;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.awt.*;

public class LiveMonitorChart extends JFrame {

    private TimeSeries cpuSeries;
    private TimeSeries memorySeries;
    private TimeSeries energySeries;

    public LiveMonitorChart() {

        setTitle("Live System Monitoring");

        setSize(900, 600);

        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 1));

        cpuSeries =
                new TimeSeries("CPU Usage");

        memorySeries =
                new TimeSeries("Memory Usage");

        energySeries =
                new TimeSeries("Energy Consumption");

        add(createChartPanel(
                cpuSeries,
                "CPU Usage"
        ));

        add(createChartPanel(
                memorySeries,
                "Memory Usage"
        ));

        add(createChartPanel(
                energySeries,
                "Energy Consumption"
        ));

        setVisible(true);
    }

    private ChartPanel createChartPanel(
            TimeSeries series,
            String title) {
    	TimeSeriesCollection dataset =
                new TimeSeriesCollection(series);

        JFreeChart chart =
                ChartFactory.createTimeSeriesChart(
                        title,
                        "Time",
                        "Value",
                        dataset,
                        false,
                        false,
                        false
                );

        return new ChartPanel(chart);
    }
    public void updateCharts(
            double cpu,
            long memory,
            double energy) {

        cpuSeries.addOrUpdate(
                new Millisecond(),
                cpu
        );

        memorySeries.addOrUpdate(
                new Millisecond(),
                memory
        );

        energySeries.addOrUpdate(
                new Millisecond(),
                energy
        );
    }
}