# Energy-Aware Java Optimizer

## Overview

Energy-Aware Java Optimizer is a Java desktop application that analyzes Java bytecode and monitors system resource usage. The project helps developers identify inefficient code patterns, analyze runtime performance, and generate optimization reports.

The application combines bytecode analysis, runtime profiling, visualization, and report generation into a single desktop tool.

---

## Features

### Bytecode Analysis
- ASM-based Java bytecode analysis
- Method counting
- Loop detection
- Object creation tracking
- Arithmetic operation analysis

### Runtime Profiling
- CPU usage monitoring
- JVM memory monitoring
- Execution time measurement
- Energy consumption estimation

### JAR File Analysis
- Analyze `.jar` files
- Scan multiple compiled classes

### Visualization
- Pie chart visualization using JFreeChart
- Live monitoring graphs
- Real-time system monitoring

### Report Generation
- TXT report export
- PDF report export
- Optimization suggestions

### GUI Dashboard
- Modern Swing-based dashboard
- File upload system
- Interactive monitoring interface

---

## Technologies Used

- Java
- Java Swing
- ASM Library
- JFreeChart
- iTextPDF

---

## Project Architecture

### Main Modules

- ASMAnalyzer
- AnalyzerService
- RuntimeProfiler
- CPUProfiler
- MemoryProfiler
- ExecutionTimer
- JarAnalyzer
- SuggestionEngine
- UsageChart
- LiveMonitorChart
- TextReportExporter
- PDFReportExporter
- Dashboard GUI

---

## Screenshots

### Dashboard
(Add Screenshot)

### Live Monitoring Graphs
(Add Screenshot)

### Pie Chart Analysis
(Add Screenshot)

### PDF Report Export
(Add Screenshot)

---

## How to Run

### 1. Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/Energy-Aware-Java-Optimizer.git
```

### 2. Open in Eclipse

Import the project into Eclipse IDE.

### 3. Add Required Libraries

Add external JAR files:
- ASM
- JFreeChart
- iTextPDF

### 4. Run Application

Run:

```text
Dashboard.java
```

---

## Future Improvements

- AI-based optimization recommendations
- Cloud report storage
- Multi-file parallel analysis
- Advanced performance metrics
- Web dashboard integration

---

## Author

Prashant Devre 

---

## License

This project is developed for educational and learning purposes.