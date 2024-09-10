// Aaron Kofman
// Zachary Gmyr
// Ali Al Hadi
// Michael Kratz

import Crops.Crop;
import DecisionMaker.DecisionMakingModule;
import Livestock.Livestock;
import SmartIrrigationSystem.SmartIrrigationSystem;
import Waste.WasteManagement;

import java.util.ArrayList;
import java.util.Scanner;


public class Testmain {
    static Scanner scanObj = new Scanner(System.in);

    public static void main(String[] args) {

        /* Smart System & DecisionMakerModule */
        SmartIrrigationSystem smartSystem = new SmartIrrigationSystem();
        DecisionMakingModule decisionMaker = new DecisionMakingModule();

        /* Initialize Livestock & Crops: */
        ArrayList<Livestock> livestock = new ArrayList<>();
        Livestock.initializeLivestock(livestock);
        ArrayList<Crop> crops = new ArrayList<>();
        Crop.initializeCrops(crops);

/////////Michael Code/////////////////////////////////
        //Prompt for info and get selection using scanner
        boolean doAgain = true;
        do {
            System.out.println("""
                    \n    ||Menu||  \s
                    1. View Sensor Data\s
                    2. Configure Settings\s
                    3. Perform Actions\s
                    4. Generate Reports\s
                    5. Manage Livestock\s
                    6. Mange Crops\s
                    7. System Information\s
                    8. Exit\s
                    """);
            System.out.print("[1-8]: ");

            int i = scanObj.nextInt();
            //Call Menu Options & Expand
            switch (i) {
                case 1:
                    System.out.println("1. Weather Conditions\n" + "2. Moisture Level\n");
                    System.out.print("[1-2]: ");
                    i = scanObj.nextInt();
                    //Sub options
                    switch (i){
                        case 1:
                            smartSystem.viewWeatherCondition();
                            break;
                        case 2:
                             smartSystem.viewMoistureLevel();
                            break;
                    }
                    break;
                // 2. Configure Settings
                case 2:
                    System.out.println("1. Irrigation Strategy - Manual Configuration\n" +
                            "2. Irrigation Strategy - Automated (advanced)\n" + "3. View Settings\n");
                    System.out.print("[1-3]: ");

                    i = scanObj.nextInt();
                    //Sub options
                    switch(i){
                        case 1:
                            smartSystem.runBasicConfiguration();
                            break;
                        case 2:
                             smartSystem.runAutomatedConfiguration();
                            break;
                        case 3:
                            smartSystem.basicStrategyModule.viewCurrentIrrigationStrategy();
                            smartSystem.advancedStrategyModule.viewCurrentIrrigationStrategy();
                            break;
                    }
                    break;
                // 3. Perform Actions
                case 3:
                    System.out.println("1. Initiate sensor readings\n2. Manage Waste\n" +
                            "3. Recycle Materials\n4. Take Emission Reduction Measures\n");
                    System.out.print("[1-4]: ");
                    i = scanObj.nextInt();
                    switch (i) {
                        case 1:
                            smartSystem.collectSensorData();
                            break;
                        case 2:
                            smartSystem.composter.manageWaste();
                            break;
                        case 3:
                            smartSystem.recycling.recycleMaterials();
                            break;
                        case 4:
                            smartSystem.emissionReduction();
                    }
                    break;
                // 4. Generate Reports
                case 4:
                    System.out.println("1. View Current Harvest\n2. View Livestock Production Metrics\n" +
                            "3. Generate Waste Management Report\n4. Generate Resource Usage Report" +
                            "\n5. Generate Emissions Report\n");
                    System.out.print("[1-5]: ");
                    i = scanObj.nextInt();
                    switch(i) {
                        case 1:
                            Crop.displayYield();
                            break;
                        case 2:
                            Livestock.displayYield();
                            break;
                        case 3:
                            WasteManagement.viewWasteManagementStatus(smartSystem.composter,smartSystem.recycling);
                            break;
                        case 4:
                            smartSystem.getResourceReport(smartSystem.advancedStrategyModule,smartSystem.basicStrategyModule);
                            break;
                        case 5:
                            smartSystem.viewEmissionsRatings();
                            break;
                    }
                    break;
                // 5. Manage Livestock
                case 5:
                    System.out.println("1. View Current Livestock\n2. Gather Livestock Vitals" +
                            "\n3. Administer Medicine to Livestock\n4. Feed \n");
                    System.out.print("[1-4]: ");
                    i = scanObj.nextInt();
                    switch (i){
                        case 1:
                            Livestock.viewListOfLivestock(livestock);
                            break;
                        case 2:
                            Livestock.trackLivestockVitals(livestock);
                            break;
                        case 3:
                            Livestock.administerMedication(livestock);
                            break;
                        case 4:
                            Livestock.feedLivestock(livestock,smartSystem);
                            break;
                    }
                    break;
                // 6. Manage Crops
                case 6:
                    System.out.println("1. View Current Crop Information\n2. Apply Fertilizer\n3. Apply Pest Control\n");
                    System.out.print("[1-3]: ");
                    i = scanObj.nextInt();
                    switch (i){
                        case 1:
                            Crop.viewCropInformation(crops);
                            break;
                        case 2:
                            decisionMaker.makeFertilizationDecision(crops,smartSystem);
                            break;
                        case 3:
                            decisionMaker.makePestControlDecision(crops,smartSystem);
                            break;
                    }
                    break;
                // 7. System Information
                case 7:
                    System.out.println("System Status - Overall Health: Good - Software Version: v1.2.0\n");
                    break;
                case 8:
                    doAgain = false;
                    break;

                default:
                    System.out.println("Please select a option (1-8)\n");
                    break;
            }
        }while (doAgain);
////////////////END OF MICHAEL CODE////////////////




    }
}
