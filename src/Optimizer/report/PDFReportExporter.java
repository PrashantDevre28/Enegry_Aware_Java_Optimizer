package Optimizer.report;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFReportExporter {

    public void exportPDF(
            AnalysisReport report,
            String filePath) {

        try {

            Document document =
                    new Document();

            PdfWriter.getInstance(
                    document,
                    new FileOutputStream(filePath)
            );

            document.open();

            document.add(
                    new Paragraph(
                            "ENERGY-AWARE JAVA OPTIMIZER REPORT"
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            document.add(
                    new Paragraph(
                            "File Name: "
                            + report.getFileName()
                    )
            );

            document.add(
                    new Paragraph(
                            "Class Name: "
                            + report.getClassName()
                    )
            );

            document.add(
                    new Paragraph(
                            "Methods: "
                            + report.getMethodCount()
                    )
            );

            document.add(
                    new Paragraph(
                            "CPU Usage: "
                            + report.getRealCpuUsage()
                            + " %"
                    )
            );

            document.add(
                    new Paragraph(
                            "Used Memory: "
                            + report.getUsedMemory()
                            + " MB"
                    )
            );

            document.add(
                    new Paragraph(
                            "Execution Time: "
                            + report.getExecutionTime()
                            + " ms"
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            document.add(
                    new Paragraph(
                            "===== SUGGESTIONS ====="
                    )
            );

            document.add(
                    new Paragraph(
                            report.getSuggestions()
                                    .toString()
                    )
            );

            document.close();

            System.out.println(
                    "PDF Report Generated"
            );

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}