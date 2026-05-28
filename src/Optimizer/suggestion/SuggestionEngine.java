package Optimizer.suggestion;

import java.util.ArrayList;
import java.util.List;

import Optimizer.profiler.Metrics;

public class SuggestionEngine {

    public static List<String> getSuggestions(
            Metrics metrics) {

        List<String> suggestions =
                new ArrayList<>();

        if(metrics.getObjectCreations() > 10) {

            suggestions.add(
                    "Reduce unnecessary object creation"
            );
        }

        if(metrics.getMethodCalls() > 20) {

            suggestions.add(
                    "Excessive method calls detected"
            );
        }

        if(metrics.getLoops() > 5) {

            suggestions.add(
                    "Optimize loops and conditions"
            );
        }

        if(metrics.getArithmeticOperations() > 15) {

            suggestions.add(
                    "Heavy arithmetic workload detected"
            );
        }

        if(suggestions.isEmpty()) {

            suggestions.add(
                    "Code looks reasonably optimized."
            );
        }

        return suggestions;
    }
}