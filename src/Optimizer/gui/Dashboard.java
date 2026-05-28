package Optimizer.gui;

import java.awt.*;

import javax.swing.*;

import Optimizer.Service.AnalyzerService;
import Optimizer.report.AnalysisReport;
import Optimizer.energy.EnergyCalculator;
import Optimizer.graph.UsageChart;
import Optimizer.profiler.RuntimeProfiler;
import Optimizer.report.ReportGenerator;

public class Dashboard extends JFrame {

    JLabel cpuLabel;
    JLabel memoryLabel;
    JLabel energyLabel;

    JTextArea suggestionArea;

    JButton analyzeButton;
    JButton uploadButton;

    public Dashboard() {

        setTitle("Energy-Aware Java Optimizer");

        setSize(700, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        getContentPane().setBackground(
                new Color(240, 248, 255)
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
                new Color(240,248,255)
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
                new Color(25,25,112)
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
                new Color(240,248,255)
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

        memoryLabel.setFont(labelFont);

        energyLabel.setFont(labelFont);

        topPanel.add(cpuLabel);

        topPanel.add(memoryLabel);

        topPanel.add(energyLabel);

        // =================================================
        // UPLOAD BUTTON
        // =================================================

        uploadButton =
                new JButton("Upload .class File");

        uploadButton.setBackground(
                new Color(34,139,34)
        );

        uploadButton.setForeground(
                Color.WHITE
        );

        topPanel.add(uploadButton);

        headerPanel.add(
                topPanel,
                BorderLayout.CENTER
        );

        add(headerPanel, BorderLayout.NORTH);

        // =================================================
        // CENTER TEXT AREA
        // =================================================

        suggestionArea =
                new JTextArea();

        suggestionArea.setBackground(
                Color.WHITE
        );

        suggestionArea.setForeground(
                Color.BLACK
        );

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

        add(analyzeButton, BorderLayout.SOUTH);

        // =================================================
        // BUTTON ACTIONS
        // =================================================

        analyzeButton.addActionListener(
                e -> analyze()
        );

        uploadButton.addActionListener(
                e -> uploadAndAnalyze()
        );

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

        int option =
                chooser.showOpenDialog(this);

        if(option == JFileChooser.APPROVE_OPTION) {

            String path =
                    chooser.getSelectedFile()
                            .getAbsolutePath();

            // ======================================
            // ASM ANALYSIS
            // ======================================

            AnalyzerService service =
                    new AnalyzerService();

            AnalysisReport report =
                    service.analyze(
                            chooser.getSelectedFile());

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
}