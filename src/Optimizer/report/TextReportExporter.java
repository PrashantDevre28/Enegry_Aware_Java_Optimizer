package Optimizer.report;

import java.io.FileWriter;

public class TextReportExporter {

    public void exportReport(
            AnalysisReport report,
            String filePath) {

        try {

            FileWriter writer =
                    new FileWriter(filePath);

            writer.write(
                    "===== ANALYSIS REPORT =====\n\n"
            );

            writer.write(
                    "File Name: "
                    + report.getFileName()
                    + "\n"
            );

            writer.write(
                    "Class Name: "
                    + report.getClassName()
                    + "\n"
            );

            writer.write(
                    "Methods: "
                    + report.getMethodCount()
                    + "\n"
            );

            writer.write(
                    "CPU Usage: "
                    + report.getRealCpuUsage()
                    + " %\n"
            );

            writer.write(
                    "Used Memory: "
                    + report.getUsedMemory()
                    + " MB\n"
            );

            writer.write(
                    "Execution Time: "
                    + report.getExecutionTime()
                    + " ms\n"
            );

            writer.write(
                    "\n===== SUGGESTIONS =====\n"
            );

            writer.write(
                    report.getSuggestions()
                            .toString()
            );

            writer.close();

            System.out.println(
                    "Report Exported Successfully"
            );

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}