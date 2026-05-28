package Optimizer.energy;

public class EnergyCalculator {
	public double calculateEnergy(
            double cpu,
            long memory,
            long time) {

        double energy =
                (cpu * 0.5)
                + (memory * 0.2)
                + (time * 0.3);

        return energy;
    }
}
