package DecisionMaker;
import Crops.Crop;
import SmartIrrigationSystem.SmartIrrigationSystem;
import Strategy.AdvancedIrrigationStrategy;

import java.util.ArrayList;

public class DecisionMakingModule implements DecisionMaker {

    @Override
    public boolean makeIrrigationDecision(AdvancedIrrigationStrategy strategy, double moisture, String weather,
                                       String soil, String topography) {
        if ( moisture >= desiredThreshold || !(weather.equals("sunny")) )
        {
            // soil moisture > 50% or it is cloudy/rainy
            System.out.println("Current moisture levels & weather conditions are satisfactory,"+
                    " irrigation not required...\n");
            return false;
        }
        else {
            // soil moisture < 50% & sunny (dry - requires irrigation)

            System.out.println("Current moisture levels & weather conditions call for irrigation adjustment, ...\n");

            // determine crop water requirements
            double cropWaterRequirement = AdvancedIrrigationStrategy.considerSoilTypeAndTopography(soil,topography);

            // make a call to advancedIrrigationStrategy
            strategy.determineIrrigationAmount(moisture,weather,cropWaterRequirement);
            strategy.adjustIrrigationSchedule(cropWaterRequirement);
            return true;
        }
    }


    /* Determines which crops are healthy and which ones have disease, then applies fertilizer to the healthy ones
    *  and reports if any others require attention */
    // should be invoked from Testmain with decisionMaker.makeFertilizationDecision(crops);
    // requires Recycling Facility & Composting Unit to be empty!
    @Override
    public void makeFertilizationDecision(ArrayList<Crop> crops, SmartIrrigationSystem smartSystem) {
        /* PSEUDOCODE:
        make an array list of healthy crops
        make an array list of unhealthy crops
        report that healthy crops will be fertilized
        report that unhealthy crops require attention before fertilization*/
        System.out.println("========== Fertilization: ==========");
        if (crops.isEmpty()) {
            System.out.println("There are no crops to fertilize!\n");
            return;
        }

        // Ensure Waste Management facilities are empty before fertilization
        if (smartSystem.composter.isFull()) {
            System.out.println("WARNING: The composting unit is full and requires attention before fertilization!\n");
            return;
        }
        else if (smartSystem.recycling.isFull()) {
            System.out.println("WARNING: The recycling facility is full and requires attention before fertilization!\n");
            return;
        }

        ArrayList<Crop> healthyCrops = new ArrayList<>();
        ArrayList<Crop> diseasedCrops = new ArrayList<>();
        for (Crop plant : crops) {
            if (plant.getCropHealth().equals("Healthy")) {
                healthyCrops.add(plant);
            } else {
                diseasedCrops.add(plant);
            }
        }
        if (!healthyCrops.isEmpty()) {
            // report that there are healthy crops & they will be applied fertilizer...
            System.out.println("\nOne or more healthy crops are being applied fertilizer...\n");
            for (Crop healthy : healthyCrops)
                SmartIrrigationSystem.applyFertilizer(healthy);

            /* increase waste */
            smartSystem.composter.addWasteOrRecyclables(smartSystem);
            smartSystem.recycling.addWasteOrRecyclables(smartSystem);

            /* increase nitrous oxide emissions */
            smartSystem.increaseEmissions(2);
        }
        if (!diseasedCrops.isEmpty()) {
            // report that there are unhealthy crops that require attention...
            System.out.println("\nThe following crops require pest control before being applied fertilizer:");
            for (Crop diseased : diseasedCrops)
                System.out.println(diseased);
            System.out.println("\n");
        }

    }

    /* Determines whether there is disease present, if so, uses pest control */
    // should be invoked from Testmain with decisionMaker.makePestControlDecision(crops);
    @Override
    public void makePestControlDecision(ArrayList<Crop> crops, SmartIrrigationSystem smartSystem) {
        System.out.println("========== Pest Control: ==========");
        if (crops.isEmpty()) {
            System.out.println("There are no crops to use pest control on!\n");
            return;
        }

        // Ensure Waste Management facilities are empty before applying pest control
        if (smartSystem.composter.isFull()) {
            System.out.println("WARNING: The composting unit is full and requires attention before applying " +
                    "pest control!\n");
            return;
        }
        else if (smartSystem.recycling.isFull()) {
            System.out.println("WARNING: The recycling facility is full and requires attention before applying " +
                    "pest control!\n");
            return;
        }

        // verify disease
        boolean diseaseDetected = false;
        ArrayList<Crop> diseasedCrops = new ArrayList<>();

        for (Crop plant : crops) {
            if (!plant.getCropHealth().equals("Healthy")) {
                diseaseDetected = true;
                diseasedCrops.add(plant);
            }
        }

        if (diseaseDetected) {
            System.out.println("The following crops have been applied pest control:");
            for (Crop plant : diseasedCrops) {
                plant.applyPestControl();
                System.out.println(plant);
            }
            smartSystem.composter.addWasteOrRecyclables(smartSystem);
            smartSystem.recycling.addWasteOrRecyclables(smartSystem);
            System.out.println("\n");
        } else
            System.out.println("No need for pest control, all crops in good health!\n");

    }
}
