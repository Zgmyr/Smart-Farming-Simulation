package Strategy;

import DecisionMaker.DecisionMakingModule;

public class BasicIrrigationStrategy extends IrrigationStrategy {

    private double irrigationDuration = 30; // (min)
    private int irrigationFrequency = 2; // (times/day)

    @Override
    public void viewCurrentIrrigationStrategy() {
        System.out.println("========== Basic Irrigation Configuration: ==========\n" +
                "Irrigation Duration: " + irrigationDuration + " min\n" +
                "Irrigation Frequency: " + irrigationFrequency + "x/day\n");
    }

    /* Getter Method - irrigationDuration */
    @Override
    public double getIrrigationDuration() {
        return irrigationDuration;
    }

    /* Getter Method - irrigationFrequency */
    @Override
    public int getIrrigationFrequency() {
        return irrigationFrequency;
    }

    /* SETTER - irrigation duration */
    // called from SmartIrrigationSystem - runBasicConfiguration()
    public void determineIrrigationAmount(double moistureLevel, String weatherCondition) {
        // This method (basic) does not take into account crop water requirement

        // Water Deficit (%) = Desired Moisture Level (50%) - Current Soil Moisture Level
        double moistureDeficit = (DecisionMakingModule.desiredThreshold) - moistureLevel; // (%)

        // adjusted deficit
        if (weatherCondition.equals("sunny"))
            moistureDeficit *= 2;

        // Irrigation Duration = Water Deficit / Irrigation Rate * Crop Water Requirement
        irrigationDuration = (moistureDeficit / irrigationRate);
        System.out.println("Soil moisture deficit = " + moistureDeficit + "%, irrigation duration adjusted...\n");
    }

    /* SETTER - irrigation frequency */
    // called from SmartIrrigationSystem - runBasicConfiguration()
    public void scheduleIrrigation() {
        System.out.print("Enter preferred irrigation schedule (times/day): ");
        int frequency = input.nextInt();

        while (frequency < 1){
            System.out.println("Please enter a value of at least '1': ");
            frequency = input.nextInt();
        }
        irrigationFrequency = frequency;
        System.out.println("\nIrrigation frequency has been adjusted...\n");
    }





}
