package Optimizer.report;

import java.io.FileWriter;

public class ReportGenerator {

    public void generateReport(
            double cpu,
            long memory,
            double energy,
            String suggestions) {

        try {

            FileWriter writer =
                    new FileWriter(
                            "Energy_Report.txt"
                    );

            writer.write(
                    "===== ENERGY ANALYSIS REPORT =====\n\n"
            );

            writer.write(
                    "CPU Usage: "
                            + cpu
                            + " %\n"
            );

            writer.write(
                    "Memory Usage: "
                            + memory
                            + " MB\n"
            );

            writer.write(
                    "Energy Consumption: "
                            + energy
                            + "\n\n"
            );

            writer.write(
                    "===== OPTIMIZATION SUGGESTIONS =====\n\n"
            );

            writer.write(suggestions);

            writer.close();

            System.out.println(
                    "Report Generated Successfully"
            );

        } catch(Exception e) {

            System.out.println(
                    "Error: "
                            + e.getMessage()
            );
        }
    }
}