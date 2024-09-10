package Strategy;

import DecisionMaker.DecisionMakingModule;
import Sensors.WeatherSensor;
import SmartIrrigationSystem.SmartIrrigationSystem;

public class AdvancedIrrigationStrategy extends IrrigationStrategy{

    private double irrigationDuration = 45; // (min)
    private int irrigationFrequency = 3; // (times/day)

    @Override
    public void viewCurrentIrrigationStrategy() {
        System.out.println("========== Advanced Irrigation Settings: ==========\n" +
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
    // called from DecisionMakingModule - makeIrrigationDecision
    public void determineIrrigationAmount(double moistureLevel, String weatherCondition, double cropWaterRequirement) {

        // Water Deficit (%) = Desired Moisture Level (50%) - Current Soil Moisture Level
        double moistureDeficit = (DecisionMakingModule.desiredThreshold) - moistureLevel; // (%)

        // adjusted deficit
        if (weatherCondition.equals("sunny"))
            moistureDeficit *= 2;

        // Irrigation Duration = Water Deficit / Irrigation Rate * Crop Water Requirement
        irrigationDuration = (moistureDeficit / irrigationRate) * cropWaterRequirement;
        System.out.println("Soil moisture deficit = " + moistureDeficit + "%, irrigation duration adjusted...\n");
    }

    /* SETTER - irrigation schedule */
    // called from DecisionMakingModule - makeIrrigationDecision
    public void adjustIrrigationSchedule(double cropWaterRequirement) {

        String predictedWeather = WeatherSensor.readWeatherForecast(); // "random" generated weather forecast

        System.out.println("Weather forecast for tomorrow is " + predictedWeather + ", irrigation frequency adjusted...\n");

        if (predictedWeather.equals("sunny") && cropWaterRequirement > 1)
            irrigationFrequency = 4;
        else if (predictedWeather.equals("sunny") && cropWaterRequirement < 1)
            irrigationFrequency = 3;
        else
            irrigationFrequency = 2;
    }

    // called from DecisionMakingModule - makeIrrigationDecision
    public static double considerSoilTypeAndTopography(String soil, String topography) {
        double waterModifier = 1;

        if (soil.equals("course"))
            waterModifier += 0.25;
        else
            waterModifier -= 0.25;

        if (topography.equals("hilly"))
            waterModifier += 0.10;
        else
            waterModifier -= 0.13;

        return waterModifier;
    }




}