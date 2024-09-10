package SmartIrrigationSystem;

import CarbonEmissions.CarbonFootprintReduction;
import CarbonEmissions.CarbonFootprintTracking;
import Crops.Crop;
import DecisionMaker.DecisionMakingModule;
import Resources.EquipmentControl;
import Resources.IrrigationSystems;
import Sensors.SoilMoistureSensor;
import Sensors.WeatherSensor;
import Strategy.AdvancedIrrigationStrategy;
import Strategy.BasicIrrigationStrategy;
import Waste.CompostingUnit;
import Waste.RecyclingFacility;
import java.util.Scanner;

// This class interacts with Sensor interface & gets current readings for moisture level
//      and weather conditions.
// Data stored in here pertains to the current farm/environment
public class SmartIrrigationSystem {

    /* ===== Variables Initialized at Startup: ===== */

    Scanner input = new Scanner(System.in);
    private double moistureLevel; // moisture level of soil (%)
    private String weatherCondition; // weather conditions (sunny,rainy,cloudy,ect.)
    private String soilType = null; // soil type (course,fine,ect.)
    private String topography = null; // landscape (hilly,flat,ect.)

    /* Sensors: */
    private final SoilMoistureSensor soilMoistureSensor = new SoilMoistureSensor();
    private final WeatherSensor weatherSensor = new WeatherSensor();

    /* Waste Management */
    public final CompostingUnit composter = new CompostingUnit();
    public final RecyclingFacility recycling = new RecyclingFacility();


    /* ===== Methods: ===== */

    public SmartIrrigationSystem() {
        System.out.println("========== Smart System Startup: ==========\n" +
                "Enter initial soil moisture (%) & weather conditions:");

        /* Use Sensors to obtain Soil Moisture & Weather Data: */
        this.moistureLevel = soilMoistureSensor.readMoistureLevel();
        this.weatherCondition = weatherSensor.readWeatherCondition();

        System.out.println("========== Smart System Startup: ==========\n" +
                "Enter soil type & topography information:");

        /* Gather Soil Type & Topography */
        gatherSoilTypeAndTopography();
    }

    /************************
     *     IRRIGATION STRATEGY & DECISION MAKER INTERACTION:
     ************************/

    // used for the advancedStrategyModule & others
    private final DecisionMakingModule decisionMaker = new DecisionMakingModule();

    // these modules each contain their own irrigationDuration (min) & irrigationFrequency (int)
    public final AdvancedIrrigationStrategy advancedStrategyModule = new AdvancedIrrigationStrategy();
    public final BasicIrrigationStrategy basicStrategyModule = new BasicIrrigationStrategy();

    /************************
     *     LIVESTOCK INTERACTION:
     ************************/

    /* Applies fertilizer to one Crop using the Crop class method fertilize() */
    // this method is invoked through the DecisionMakerModule
    public static void applyFertilizer(Crop plant) {
        plant.fertilize();
    }

    /************************
     *     RESOURCE OPTIMIZATION INTERACTION:
     ************************/

    /* Resource Optimization */
    public final IrrigationSystems irrigationSystemModule = new IrrigationSystems();
    public final EquipmentControl equipmentControlModule = new EquipmentControl();

    /* Show the current resource usage (water & energy consumption) from both the basic & advanced irrigation
    *   strategies */
    // Dependent upon sensor data gathered from AdvancedIrrigationStrategyModule & BasicIrrigationStrategyModule
    // Coordinates between the strategy modules and the Resource Optimization interface
    public void getResourceReport(AdvancedIrrigationStrategy advModule, BasicIrrigationStrategy basicModule) {
        System.out.println("========== Resource Usage Report: ==========");
        System.out.println("\nWater Usage:");
        this.irrigationSystemModule.resourceReport(advModule,basicModule);
        System.out.println("\nEnergy Usage:");
        this.equipmentControlModule.resourceReport(advModule,basicModule);
    }

    /************************
     *     CARBON FOOTPRINT INTERACTION:
     ************************/

    /* Carbon Footprint */
    public final CarbonFootprintTracking emissionsTracker = new CarbonFootprintTracking();
    public final CarbonFootprintReduction emissionsReductionModule = new CarbonFootprintReduction();

    /* Reports emissions from each category to user */
    public void viewEmissionsRatings() {
        System.out.println("========== Emissions Report: ==========");
        this.emissionsTracker.trackEmissions();
        System.out.println("\n");
    }

    /* Incrementor - updates corresponding emission type by making a call to CarbonFootprintTracking - increase()
    *  ... should be implemented in various menu options that affect the corresponding emission type:
    * - Methane: feedLivestock()
    * - Nitrous Oxide: makeFertilizationDecision
    * - Carbon Dioxide: when composter is full */
    public void increaseEmissions(int emissionsType) {
        this.emissionsTracker.increase(emissionsType);
    }

    /* Reduces all emissions and reports new emission score to user */
    // implement this as a menu action
    public void emissionReduction() {
        System.out.println("========== Reducing Emissions: ==========");
        this.emissionsReductionModule.reduceEmissions(emissionsTracker);
        System.out.println("Emissions reduced in all categories...\n" +
                "Generating new emissions report...\n");
        this.emissionsTracker.trackEmissions();
        System.out.println("\n");
    }


    /************************
    *     SETTER METHODS:
    ************************/


    /* Responsible for coordinating with the sensors, DecisionMaker module and Advanced Irrigation Strategy
    *  ... invokes determineIrrigationAmount, adjustIrrigationSchedule, and considerSoilTypeAndTopography
    *  to adjust irrigation duration & frequency for the Advanced Irrigation Strategy class */
     public void runAutomatedConfiguration() {
         collectSensorData();
         boolean changesMade = decisionMaker.makeIrrigationDecision(advancedStrategyModule, moistureLevel, weatherCondition,
                 soilType, topography);

         if (changesMade)
             System.out.println("Changes successfully applied to Advanced Irrigation Settings...\n");
         advancedStrategyModule.viewCurrentIrrigationStrategy();
     }

     /* Responsible for coordinating with the sensors and Basic Irrigation Strategy
     *  ... invokes determineIrrigationAmount & scheduleIrrigation to adjust irrigation duration &
     *  frequency for the Basic Irrigation Strategy class */
     public void runBasicConfiguration() {
         collectSensorData();
         basicStrategyModule.determineIrrigationAmount(moistureLevel,weatherCondition);
         basicStrategyModule.scheduleIrrigation();

         System.out.println("Changes successfully applied to Basic Irrigation Settings...\n");
     }

    // Setter - moistureLevel & weatherCondition
    public void collectSensorData() {
        System.out.println("========== Sensor Readings: ==========");
        this.moistureLevel = soilMoistureSensor.readMoistureLevel();
        this.weatherCondition = weatherSensor.readWeatherCondition();
        System.out.println("... moisture level & weather conditions have been updated.\n");
    }

    /* Setter - soilType & Topography */
    private void gatherSoilTypeAndTopography() {
        boolean invalidCondition = true;
        String soilType;
        String topography;

        do {
            System.out.print("[soil type] (course/fine): ");
            soilType = input.next().toLowerCase();
            if (soilType.equals("course") || soilType.equals("fine")) {
                invalidCondition = false;
                this.soilType = soilType;
            }
        } while (invalidCondition);

        invalidCondition = true;
        do {
            System.out.print("[topography] (hilly/flat): ");
            topography = input.next().toLowerCase();
            if (topography.equals("hilly") || topography.equals("flat")) {
                invalidCondition = false;
                this.topography = topography;
            }
        } while (invalidCondition);
    }


    /************************
    *   GETTER METHODS:
    ************************/


    /* Getter - moistureLevel */
    // should be used by decision maker module
    public double getMoistureLevel() {
        return moistureLevel;
    }
    /* Print - moistureLevel */
    public void viewMoistureLevel() {
        System.out.println("[Current Soil Moisture Level]: " + moistureLevel + "%\n");
    }

    /* Getter - weatherCondition */
    // should be used by decision maker module
    public String getWeatherCondition() {
        return weatherCondition;
    }
    /* Print - weatherCondition */
    public void viewWeatherCondition() {
        System.out.println("[Current Weather Conditions]: " + weatherCondition + "\n");
    }

    /* Getter - soilType */
    // should be used by decision maker module - considerSoilTypeAndTopography()
    public String getSoilType() {
        return soilType;
    }

    /* Getter - topography */
    // should be used by decision maker module - considerSoilTypeAndTopography()
    public String getTopography() {
        return topography;
    }








    // UNIT TESTING - Ignore this...
    public static void main(String[] args) {
        // initialize the system
        SmartIrrigationSystem smartSystem = new SmartIrrigationSystem();


        // read current sensor data
        System.out.println("\nDEBUG: view current conditions:\n");
        smartSystem.advancedStrategyModule.viewCurrentIrrigationStrategy();
        smartSystem.runAutomatedConfiguration();
    }



}
