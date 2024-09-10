package Resources;

import Strategy.AdvancedIrrigationStrategy;
import Strategy.BasicIrrigationStrategy;
import Strategy.IrrigationStrategy;

public class IrrigationSystems implements ResourceOptimization {

    @Override
    public void resourceReport(AdvancedIrrigationStrategy advModule, BasicIrrigationStrategy basicModule) {
        double advancedIrrigationWaterUsage = optimizeWaterUsage(advModule);
        System.out.println("Water Usage (Adv. Irrigation Strategy): " + advancedIrrigationWaterUsage +
                " gal/day");

        double basicIrrigationWaterUsage = optimizeWaterUsage(basicModule);
        System.out.println("Water Usage (Basic Irrigation Strategy): " + basicIrrigationWaterUsage +
                " gal/day");
    }

    @Override
    public double optimizeWaterUsage(IrrigationStrategy strategy) {
        double irrigationDuration = strategy.getIrrigationDuration(); // minutes
        int irrigationFrequency = strategy.getIrrigationFrequency(); // times per day

        // WaterUsagePerSession (gal) = Irrigation Duration (min) * Irrigation Rate (1 gal/min)
        double waterUsagePerSession = irrigationDuration * IrrigationStrategy.irrigationRate;

        // WaterUsagePerDay (gal/day) = Water Usage per Session (gal) * # Sessions per day (frequency, or times/day)
        return (waterUsagePerSession * irrigationFrequency);
    }

    /* This class does not implement energy optimization, instead use EquipmentControl for this*/
    @Override
    public double optimizeEnergyUsage(IrrigationStrategy strategy) {
        return -1;
    }
}
