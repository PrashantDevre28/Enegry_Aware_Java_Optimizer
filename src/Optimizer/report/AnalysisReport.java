package Optimizer.report;

import java.util.List;

public class AnalysisReport {
	 private String className;
	 private int methodCount;
	 private double cpuUsage;
	 private double memoryUsage;
	 private long executionTime;
	 private double energyScore;
	 private List<String> suggestions;
	 private int methodCalls;
	 private int objectCreations;
	 private int loops;
	 private int arithmeticOperations;
	 private String fileName;
	 private long fileSize;
	 private String analyzedAt;
	 private boolean success;
	 private String errorMessage;
	 private long analysisDuration;
	 
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public long getAnalysisDuration() {
		return analysisDuration;
	}
	public void setAnalysisDuration(long analysisDuration) {
		this.analysisDuration = analysisDuration;
	}
	public int getMethodCalls() {
		return methodCalls;
	}
	public void setMethodCalls(int methodCalls) {
		this.methodCalls = methodCalls;
	}
	public int getObjectCreations() {
		return objectCreations;
	}
	public void setObjectCreations(int objectCreations) {
		this.objectCreations = objectCreations;
	}
	public int getLoops() {
		return loops;
	}
	public void setLoops(int loops) {
		this.loops = loops;
	}
	public int getArithmeticOperations() {
		return arithmeticOperations;
	}
	public void setArithmeticOperations(int arithmeticOperations) {
		this.arithmeticOperations = arithmeticOperations;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getMethodCount() {
		return methodCount;
	}
	public void setMethodCount(int methodCount) {
		this.methodCount = methodCount;
	}
	public double getCpuUsage() {
		return cpuUsage;
	}
	public void setCpuUsage(double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	public double getMemoryUsage() {
		return memoryUsage;
	}
	public void setMemoryUsage(double memoryUsage) {
		this.memoryUsage = memoryUsage;
	}
	public long getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}
	public double getEnergyScore() {
		return energyScore;
	}
	public void setEnergyScore(double energyScore) {
		this.energyScore = energyScore;
	}
	public List<String> getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(List<String> suggestions) {
		this.suggestions = suggestions;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getAnalyzedAt() {
		return analyzedAt;
	}
	public void setAnalyzedAt(String analyzedAt) {
		this.analyzedAt = analyzedAt;
	}
	@Override
	public String toString() {
		StringBuilder builder= new StringBuilder();
		builder.append("=========== ANALYSIS REPORT ============= \n\n");
		
		builder.append("Status: ");

		if(success) {

		    builder.append("SUCCESS\n\n");

		} else {

		    builder.append("FAILED\n");

		    builder.append("Error: ")
		           .append(errorMessage)
		           .append("\n\n");
		}
		
		builder.append("File Name: ")
	       .append(fileName)
	       .append("\n");

		builder.append("File Size: ")
		       .append(fileSize)
		       .append(" bytes\n");

		builder.append("Analyzed At: ")
		       .append(analyzedAt)
		       .append("\n\n");
		
		builder.append("Class Name:  ")
				.append(className)
				.append("\n");
		builder.append("Method Count: ")
        .append(methodCount)
        .append("\n");

		builder.append("CPU Usage: ")
		       .append(cpuUsage)
		       .append(" %\n");

		builder.append("Memory Usage: ")
		       .append(memoryUsage)
		       .append(" MB\n");

		builder.append("Execution Time: ")
		       .append(executionTime)
		       .append(" ms\n");

		builder.append("Energy Score: ")
		       .append(energyScore)
		       .append("\n\n");
		
		builder.append("Method Calls: ")
			   .append(methodCalls)
			   .append("\n");

		builder.append("Object Creations: ")
	       	   .append(objectCreations)
	       	   .append("\n");

		builder.append("Loops: ")
		       .append(loops)
		       .append("\n");

		builder.append("Arithmetic Operations: ")
		       .append(arithmeticOperations)
		       .append("\n");
		builder.append("Suggestions:\n");
	    
		builder.append("Analysis Duration: ")
		       .append(analysisDuration)
		       .append(" ms\n");
		
        if (suggestions != null) {

            for (String suggestion : suggestions) {
                builder.append("- ")
                       .append(suggestion)
                       .append("\n");
            }
        }
        return builder.toString();
	}
	
}
    