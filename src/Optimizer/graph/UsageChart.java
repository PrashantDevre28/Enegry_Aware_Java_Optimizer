package Optimizer.graph;

import org.jfree.chart.*;

import org.jfree.data.general.DefaultPieDataset;

public class UsageChart {
	public void showChart(
            double cpu,
            long memory,
            double energy) {

        DefaultPieDataset dataset =
                new DefaultPieDataset();

        dataset.setValue("CPU Usage", cpu);

        dataset.setValue("Memory Usage", memory);

        dataset.setValue("Energy", energy);

        JFreeChart chart =
                ChartFactory.createPieChart(
                        "System Usage Analysis",
                        dataset,
                        true,
                        true,
                        false
                );

        ChartFrame frame =
                new ChartFrame(
                        "Usage Graph",
                        chart
                );

        frame.setSize(600, 500);

        frame.setVisible(true);
    }
}
