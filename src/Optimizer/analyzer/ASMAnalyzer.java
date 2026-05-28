package Optimizer.analyzer;

import org.objectweb.asm.*;

import java.io.FileInputStream;

import Optimizer.profiler.Metrics;
import Optimizer.suggestion.SuggestionEngine;

public class ASMAnalyzer {

    private Metrics metrics;
    private int cpuUsage;
    private int memoryUsage;
    private int energyConsumption;
    private String className;

    public ASMAnalyzer() {
    	
        metrics = new Metrics();
    }

    public String analyzeClass(String classFilePath) {
    	
        StringBuilder result =
                new StringBuilder();

        try {

            FileInputStream fis =
                    new FileInputStream(classFilePath);

            ClassReader reader =
                    new ClassReader(fis);

            result.append(
                    "===== BYTECODE ANALYSIS REPORT =====\n"
            );

            reader.accept(new ClassVisitor(Opcodes.ASM9) {
            	@Override
            	public void visit(
            	        int version,
            	        int access,
            	        String name,
            	        String signature,
            	        String superName,
            	        String[] interfaces) {

            	    className = name;
            	}

                @Override
                public MethodVisitor visitMethod(
                        int access,
                        String name,
                        String descriptor,
                        String signature,
                        String[] exceptions) {

                    metrics.incrementMethods();

                    result.append(
                            "\nMethod Found: "
                                    + name
                                    + "\n"
                    );

                    return new MethodVisitor(Opcodes.ASM9) {

                        @Override
                        public void visitTypeInsn(
                                int opcode,
                                String type) {

                            if(opcode == Opcodes.NEW) {

                                metrics.incrementObjectCreations();

                                result.append(
                                        "Object Creation Detected: "
                                                + type
                                                + "\n"
                                );
                            }
                        }

                        @Override
                        public void visitInsn(int opcode) {

                            // Arithmetic operations

                            if(opcode == Opcodes.IADD
                                    || opcode == Opcodes.ISUB
                                    || opcode == Opcodes.IMUL
                                    || opcode == Opcodes.IDIV) {

                                metrics.incrementArithmeticOperations();

                                result.append(
                                        "Arithmetic Operation Detected\n"
                                );
                            }
                        }

                        @Override
                        public void visitJumpInsn(
                                int opcode,
                                Label label) {

                            metrics.incrementLoops();

                            result.append(
                                    "Loop/Conditional Detected\n"
                            );
                        }

                        @Override
                        public void visitMethodInsn(
                                int opcode,
                                String owner,
                                String name,
                                String descriptor,
                                boolean isInterface) {

                            metrics.incrementMethodCalls();

                            result.append(
                                    "Method Call Detected: "
                                            + name
                                            + "\n"
                            );
                        }
                    };
                }

            }, 0);

            fis.close();

            // ===== CALCULATED METRICS =====

            cpuUsage =
                    (metrics.getMethodCalls() * 2)
                    + (metrics.getLoops() * 3)
                    + (metrics.getArithmeticOperations());

            memoryUsage =
                    metrics.getObjectCreations() * 5;

            energyConsumption =
                    (cpuUsage / 2)
                    + (memoryUsage / 3);

            // ===== FINAL REPORT =====

            result.append("\n");
            result.append("===== PERFORMANCE METRICS =====\n");

            result.append(
                    "Methods Found: "
                            + metrics.getMethods()
                            + "\n"
            );

            result.append(
                    "Method Calls: "
                            + metrics.getMethodCalls()
                            + "\n"
            );

            result.append(
                    "Object Creations: "
                            + metrics.getObjectCreations()
                            + "\n"
            );

            result.append(
                    "Loops/Conditions: "
                            + metrics.getLoops()
                            + "\n"
            );

            result.append(
                    "Arithmetic Operations: "
                            + metrics.getArithmeticOperations()
                            + "\n"
            );

            result.append(
                    "\nEstimated CPU Usage: "
                            + cpuUsage
                            + "\n"
            );

            result.append(
                    "Estimated Memory Usage: "
                            + memoryUsage
                            + " MB\n"
            );

            result.append(
                    "Estimated Energy Consumption: "
                            + energyConsumption
                            + " Watts\n"
            );

            // ===== SUGGESTIONS =====

            result.append("\n");
            result.append(
                    "===== OPTIMIZATION SUGGESTIONS =====\n"
            );

            

        } catch(Exception e) {

            result.append(
                    "Error: "
                            + e.getMessage()
            );
        }

        return result.toString();
    }
    public Metrics getMetrics() {
		return metrics;
	}

	public int getCpuUsage() {
		return cpuUsage;
	}

	public int getMemoryUsage() {
		return memoryUsage;
	}
	public int getEnergyConsumption() {
		return energyConsumption;
	}
	public String getClassName() {
		return className;
	}
}