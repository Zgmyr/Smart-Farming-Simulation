package DecisionMaker;

import Crops.Crop;
import SmartIrrigationSystem.SmartIrrigationSystem;
import Strategy.AdvancedIrrigationStrategy;

import java.util.ArrayList;

public interface DecisionMaker {
    double desiredThreshold = 50; // desired threshold is 50% moisture level

    boolean makeIrrigationDecision(AdvancedIrrigationStrategy strategy, double moisture, String weather,
                                String soil, String topography);
    void makeFertilizationDecision(ArrayList<Crop> crops, SmartIrrigationSystem smartSystem);
    void makePestControlDecision(ArrayList<Crop> crops, SmartIrrigationSystem smartSystem);


}
