package Resources;

import Strategy.AdvancedIrrigationStrategy;
import Strategy.BasicIrrigationStrategy;
import Strategy.IrrigationStrategy;

public class EquipmentControl implements ResourceOptimization {

    @Override
    public void resourceReport(AdvancedIrrigationStrategy advModule, BasicIrrigationStrategy basicModule) {
        double advancedIrrigationEnergyUsage = optimizeEnergyUsage(advModule);
        System.out.println("Energy Usage (Adv. Irrigation Strategy): " + advancedIrrigationEnergyUsage +
                " kWh/day");

        double basicIrrigationEnergyUsage = optimizeEnergyUsage(basicModule);
        System.out.println("Energy Usage (Basic Irrigation Strategy): " + basicIrrigationEnergyUsage +
                " kWh/day");
    }

    @Override
    public double optimizeEnergyUsage(IrrigationStrategy strategy) {
        // Duration of Operation (hours/day) = (Irrigation Duration (min) / 60) * Irrigation Frequency (times/day)
        double durationOfOperation = (strategy.getIrrigationDuration() / 60) * strategy.getIrrigationFrequency();

        // energyUsagePerDay (kWh/day) = Power Consumption (kW) * Duration of Operation (hours/day)
        return (irrigationEquipmentPowerUsage * durationOfOperation);

    }

    /* This class does not implement water optimization, instead use IrrigationSystems for this */
    @Override
    public double optimizeWaterUsage(IrrigationStrategy strategy) {
        return -1;
    }
}
