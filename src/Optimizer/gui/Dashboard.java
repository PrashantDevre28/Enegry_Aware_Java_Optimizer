package Optimizer.gui;

import java.awt.*;
import java.io.File;

import javax.swing.*;

import Optimizer.Service.AnalyzerService;
import Optimizer.analyzer.JarAnalyzer;
import Optimizer.report.AnalysisReport;
import Optimizer.report.PDFReportExporter;
import Optimizer.energy.EnergyCalculator;
import Optimizer.graph.LiveMonitorChart;
import Optimizer.graph.UsageChart;
import Optimizer.profiler.RuntimeProfiler;
import Optimizer.report.ReportGenerator;
import Optimizer.report.TextReportExporter;

public class Dashboard extends JFrame {

    JLabel cpuLabel;
    JLabel memoryLabel;
    JLabel energyLabel;

    JTextArea suggestionArea;

    JButton analyzeButton;
    JButton uploadButton;
    JButton exportButton;
    JButton pdfButton;
    
    AnalysisReport lastReport;
    LiveMonitorChart liveChart;

    public Dashboard() {

        setTitle("Energy-Aware Java Optimizer");

        setSize(1000, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        getContentPane().setBackground(
                new Color(30,30,30)
        );

        // =================================================
        // HEADER PANEL
        // =================================================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setLayout(
                new BorderLayout()
        );

        headerPanel.setBackground(
                new Color(20,20,20)
        );

        // =================================================
        // TITLE
        // =================================================

        JLabel title =
                new JLabel(
                        "Energy-Aware Java Optimizer",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                new Color(0,255,180)
        );

        headerPanel.add(
                title,
                BorderLayout.NORTH
        );

        // =================================================
        // TOP INFO PANEL
        // =================================================

        JPanel topPanel =
                new JPanel();

        topPanel.setBackground(
                new Color(30,30,30)
        );

        topPanel.setLayout(
                new GridLayout(4,1,5,5)
        );

        cpuLabel =
                new JLabel("CPU Usage: ");

        memoryLabel =
                new JLabel("Memory Usage: ");

        energyLabel =
                new JLabel("Energy Consumption: ");

        Font labelFont =
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                );

        cpuLabel.setFont(labelFont);
        cpuLabel.setForeground(Color.white);

        memoryLabel.setFont(labelFont);
        memoryLabel.setForeground(Color.white);

        energyLabel.setFont(labelFont);
        energyLabel.setForeground(Color.white);
        

        topPanel.add(cpuLabel);

        topPanel.add(memoryLabel);

        topPanel.add(energyLabel);
        
        
        // =================================================
        // UPLOAD BUTTON
        // =================================================

        uploadButton = new JButton("Upload .class File");

        uploadButton.setBackground(new Color(34,139,34) );

        uploadButton.setForeground(Color.WHITE);
        
        uploadButton.setFocusPainted(false);
        uploadButton.setFont(new Font("Arial",Font.BOLD,15));

        topPanel.add(uploadButton);

        headerPanel.add(
                topPanel,
                BorderLayout.CENTER
        );
        
        pdfButton = new JButton("Export PDF");

        pdfButton.setBackground( new Color(178,34,34));

        pdfButton.setForeground(Color.WHITE);
        pdfButton.setFocusPainted(false);
        pdfButton.setFont(new Font("Arial",Font.BOLD,15));
        
        add(headerPanel, BorderLayout.NORTH);

        // =================================================
        // CENTER TEXT AREA
        // =================================================

        suggestionArea =
                new JTextArea();

        suggestionArea.setBackground(new Color(15,15,15));

        suggestionArea.setForeground(new Color(0,255,140));

        suggestionArea.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        16
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        suggestionArea
                );

        add(scrollPane, BorderLayout.CENTER);

        // =================================================
        // ANALYZE BUTTON
        // =================================================

        analyzeButton =
                new JButton("Analyze Application");

        analyzeButton.setBackground(
                new Color(70,130,180)
        );

        analyzeButton.setForeground(
                Color.WHITE
        );
        analyzeButton.setFocusPainted(false);
        analyzeButton.setFont(new Font("Arial",Font.BOLD,15));
        
        exportButton = new JButton("Export Report");

        exportButton.setBackground(new Color(139,0,139));

        exportButton.setForeground(Color.WHITE);
        exportButton.setFocusPainted(false);
        exportButton.setFont(new Font("Arial",Font.BOLD,15));
        
        JPanel bottomPanel =
                new JPanel();

        bottomPanel.add(analyzeButton);

        bottomPanel.add(exportButton);
        
        bottomPanel.add(pdfButton);
        bottomPanel.setBackground(new Color(20,20,20));
        add(bottomPanel, BorderLayout.SOUTH);
        
        

        // =================================================
        // BUTTON ACTIONS
        // =================================================

        analyzeButton.addActionListener(e -> analyze());

        uploadButton.addActionListener(e -> uploadAndAnalyze());
        
        exportButton.addActionListener(e -> exportReport());
        
        pdfButton.addActionListener(e -> exportPDF());
        
        liveChart= new LiveMonitorChart();
        
        startLiveMonitoring();
        setVisible(true);
    }

    // =================================================
    // ANALYZE METHOD
    // =================================================

    public void analyze() {

        RuntimeProfiler profiler =
                new RuntimeProfiler();

        double cpu =
                profiler.getCpuUsage();

        long memory =
                profiler.getMemoryUsage();

        long start =
                System.currentTimeMillis();

        // Dummy workload
        for(int i = 0; i < 1000000; i++) {

            Math.sqrt(i);
        }

        long end =
                System.currentTimeMillis();

        long executionTime =
                end - start;

        EnergyCalculator calculator =
                new EnergyCalculator();

        double energy =
                calculator.calculateEnergy(
                        cpu,
                        memory,
                        executionTime
                );

        cpuLabel.setText(
                "CPU Usage: "
                        + String.format("%.2f", cpu)
                        + " %"
        );

        memoryLabel.setText(
                "Memory Usage: "
                        + memory
                        + " MB"
        );

        energyLabel.setText(
                "Energy Consumption: "
                        + String.format("%.2f", energy)
        );

        // =================================================
        // SHOW CHART
        // =================================================

        UsageChart chart =
                new UsageChart();

        chart.showChart(
                cpu,
                memory,
                energy
        );

        // =================================================
        // SUGGESTIONS
        // =================================================

        suggestionArea.setText(

                "===== OPTIMIZATION SUGGESTIONS =====\n\n"

                + "1. Reduce unnecessary loops\n\n"

                + "2. Reuse objects instead of creating new ones\n\n"

                + "3. Use StringBuilder instead of String concatenation\n\n"

                + "4. Avoid repeated calculations\n\n"

                + "5. Use efficient algorithms and data structures\n"
        );

        // =================================================
        // GENERATE REPORT
        // =================================================

        String suggestions =
                suggestionArea.getText();

        ReportGenerator report =
                new ReportGenerator();

        report.generateReport(
                cpu,
                memory,
                energy,
                suggestions
        );
    }

    // =================================================
    // FILE UPLOAD + ASM ANALYSIS
    // =================================================

    public void uploadAndAnalyze() {

        JFileChooser chooser =
                new JFileChooser();
        chooser.setFileFilter(
        	    new javax.swing.filechooser.FileNameExtensionFilter(
        	            "Class and Jar Files",
        	            "class",
        	            "jar"
        	    )
        	);

        int option =
                chooser.showOpenDialog(this);

        if(option == JFileChooser.APPROVE_OPTION) {

            String path =
                    chooser.getSelectedFile()
                            .getAbsolutePath();
            File selectedFile =
                    chooser.getSelectedFile();
            
            // ======================================
            // ASM ANALYSIS
            // ======================================

            if(path.endsWith(".jar")) {

                JarAnalyzer jarAnalyzer =
                        new JarAnalyzer();

                jarAnalyzer.analyzeJar(
                        selectedFile
                );

                suggestionArea.setText(
                        "JAR analysis completed."
                );

                return;
            }

            AnalyzerService service =
                    new AnalyzerService();

            AnalysisReport report =
                    service.analyze(selectedFile);
            lastReport = report;
            liveChart.updateCharts(report.getRealCpuUsage(),
            		report.getUsedMemory(),
            		report.getEnergyScore());
            // ======================================
            // UPDATE LABELS
            // ======================================

            cpuLabel.setText(
                    "CPU Usage: "
                            + report.getCpuUsage()
                            + " %"
            );

            memoryLabel.setText(
                    "Memory Usage: "
                            + report.getMemoryUsage()
                            + " MB"
            );

            energyLabel.setText(
                    "Energy Consumption: "
                            + report.getEnergyScore()
            );

            // ======================================
            // SHOW OUTPUT
            // ======================================

            suggestionArea.setText(

                    report.toString()

                    + "\n\n===== EXECUTION REPORT =====\n"

                    + "\nExecution Time: "
                    + report.getExecutionTime()
                    + " ms\n"

                    + "\n===== MEMORY REPORT =====\n"

                    + "\nUsed Memory : "
                    + report.getUsedMemory()
                    + " MB"

                    + "\nFree Memory : "
                    + report.getFreeMemory()
                    + " MB"

                    + "\nTotal Memory: "
                    + report.getTotalMemory()
                    + " MB"

                    + "\nMax Memory  : "
                    + report.getMaxMemory()
                    + " MB\n"
                    
					+ "\n===== CPU REPORT =====\n"
					
					+ "\nReal CPU Usage: "
					+ String.format(
					        "%.2f",
					        report.getRealCpuUsage()
					)
					+ " %\n"
            );

            // ======================================
            // SHOW CHART
            // ======================================

            UsageChart chart =
                    new UsageChart();

            chart.showChart(
                    report.getCpuUsage(),
                    (long) report.getMemoryUsage(),
                    report.getEnergyScore()
            );
        }
    }
    public void exportReport() {

        if(lastReport == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No report available!"
            );

            return;
        }

        JFileChooser chooser =
                new JFileChooser();

        int option =
                chooser.showSaveDialog(this);

        if(option ==
                JFileChooser.APPROVE_OPTION) {

            String path =
                    chooser.getSelectedFile()
                            .getAbsolutePath();

            if(!path.endsWith(".txt")) {

                path += ".txt";
            }

            TextReportExporter exporter =
                    new TextReportExporter();

            exporter.exportReport(
                    lastReport,
                    path
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Report Exported!"
            );
        }
    }
    public void exportPDF() {

        if(lastReport == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No report available!"
            );

            return;
        }

        JFileChooser chooser =
                new JFileChooser();

        int option =
                chooser.showSaveDialog(this);

        if(option ==
                JFileChooser.APPROVE_OPTION) {

            String path =
                    chooser.getSelectedFile()
                            .getAbsolutePath();

            if(!path.endsWith(".pdf")) {

                path += ".pdf";
            }

            PDFReportExporter exporter =
                    new PDFReportExporter();

            exporter.exportPDF(
                    lastReport,
                    path
            );

            JOptionPane.showMessageDialog(
                    this,
                    "PDF Exported!"
            );
        }
    }
    public void startLiveMonitoring() {

        Timer timer =
                new Timer(2000, e -> {

            RuntimeProfiler profiler =
                    new RuntimeProfiler();

            double cpu =
                    profiler.getCpuUsage();

            long memory =
                    profiler.getMemoryUsage();

            double energy =
                    (cpu * memory) / 100;

            liveChart.updateCharts(
                    cpu,
                    memory,
                    energy
            );
        });

        timer.start();
    }
}