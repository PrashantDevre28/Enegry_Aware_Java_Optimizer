package Optimizer.Service;

import java.io.File;
import Optimizer.suggestion.SuggestionEngine;
import Optimizer.analyzer.ASMAnalyzer;
import Optimizer.report.AnalysisReport;
import java.time.LocalDateTime;
import Optimizer.profiler.ExecutionTimer;
import Optimizer.profiler.MemoryProfiler;
import Optimizer.profiler.CPUProfiler;


public class AnalyzerService {

	public AnalysisReport analyze(File classFile) {

	    AnalysisReport report =
	            new AnalysisReport();
	    
	    ExecutionTimer timer =
	    		new ExecutionTimer();
	    timer.start();
	    
	    MemoryProfiler memoryProfiler=
	    		new MemoryProfiler();
	    memoryProfiler.analyzeMemory();
	    
	    CPUProfiler cpuProfiler = new CPUProfiler();
	    
	    try {

	        ASMAnalyzer analyzer =
	                new ASMAnalyzer();

	        analyzer.analyzeClass(
	                classFile.getAbsolutePath());

	        report.setSuccess(true);

	        report.setFileName(
	                classFile.getName());

	        report.setFileSize(
	                classFile.length());

	        report.setAnalyzedAt(
	                LocalDateTime.now().toString());
	        
	        report.setUsedMemory(
	                memoryProfiler.getUsedMemoryMB());

	        report.setFreeMemory(
	                memoryProfiler.getFreeMemoryMB());

	        report.setTotalMemory(
	                memoryProfiler.getTotalMemoryMB());

	        report.setMaxMemory(
	                memoryProfiler.getMaxMemoryMB());

	        report.setClassName(
	                analyzer.getClassName());

	        report.setMethodCount(
	                analyzer.getMetrics().getMethods());
	        
	        report.setRealCpuUsage(
	        		cpuProfiler.getCpuUsage());
	        
	        report.setCpuUsage(
	                analyzer.getCpuUsage());

	        report.setMemoryUsage(
	                analyzer.getMemoryUsage());

	        report.setEnergyScore(
	                analyzer.getEnergyConsumption());

	        report.setMethodCalls(
	                analyzer.getMetrics()
	                        .getMethodCalls());

	        report.setObjectCreations(
	                analyzer.getMetrics()
	                        .getObjectCreations());

	        report.setLoops(
	                analyzer.getMetrics()
	                        .getLoops());

	        report.setArithmeticOperations(
	                analyzer.getMetrics()
	                        .getArithmeticOperations());

	        report.setSuggestions(
	                SuggestionEngine.getSuggestions(
	                        analyzer.getMetrics())
	        );

	    } catch(Exception e) {

	        report.setSuccess(false);

	        report.setErrorMessage(
	                e.getMessage());
	    }
	    timer.stop();
	    report.setExecutionTime(
	    		timer.getExecutionTimeMillis());
	    return report;
	}
}