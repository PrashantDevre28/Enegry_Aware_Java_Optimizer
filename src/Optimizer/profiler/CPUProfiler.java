package Optimizer.profiler;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class CPUProfiler {

    public double getCpuUsage() {

        OperatingSystemMXBean osBean =
                ManagementFactory
                        .getOperatingSystemMXBean();

        double load =
                osBean.getSystemLoadAverage();

        if(load < 0) {

            load = 0;
        }

        return load;
    }
}