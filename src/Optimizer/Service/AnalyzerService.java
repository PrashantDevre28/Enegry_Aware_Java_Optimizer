package Optimizer.Service;

import java.io.File;
import java.util.Arrays;
import Optimizer.suggestion.SuggestionEngine;
import Optimizer.analyzer.ASMAnalyzer;
import Optimizer.report.AnalysisReport;
import java.time.LocalDateTime;


public class AnalyzerService {

	public AnalysisReport analyze(File classFile) {

	    AnalysisReport report =
	            new AnalysisReport();

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

	        report.setClassName(
	                analyzer.getClassName());

	        report.setMethodCount(
	                analyzer.getMetrics().getMethods());

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

	    return report;
	}
}