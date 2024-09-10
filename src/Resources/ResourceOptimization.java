package Resources;

import Strategy.AdvancedIrrigationStrategy;
import Strategy.BasicIrrigationStrategy;
import Strategy.IrrigationStrategy;

public interface ResourceOptimization {

    double irrigationEquipmentPowerUsage = 1.5; // (kW) constant, defined by equipment specifications

    void resourceReport(AdvancedIrrigationStrategy advModule, BasicIrrigationStrategy basicModule);
    double optimizeWaterUsage(IrrigationStrategy strategy);
    double optimizeEnergyUsage(IrrigationStrategy strategy);
}
