package Crops;

import Livestock.Cattle;
import Livestock.Poultry;
import Livestock.Sheep;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Crop {
    static Scanner input = new Scanner(System.in);
    private String cropType = null;
    private String growthStage;

    /* growth stage variables */
    private int currentStage = 0;
    final String[] stages = {"Sprouting","Seeding","Vegetative","Reproductive","Ripening"};

    /* updates when crop passes "Ripening" stage */
    static int[] currentYield = {0,0,0};  // units {Wheat, Corn, Tomatoes}

    public Crop(String type) {
        this.growthStage = stages[currentStage];
        this.cropType = type;
    }

    /* Prints information about a singular crop */
    @Override
    public String toString() {
        return("Type: " + cropType + " - Growth Stage: " +
                growthStage);
    }

    /* Displays Crop Information & Reports if one or more crops have disease */

    public static void viewCropInformation(ArrayList<Crop> crops) {
        System.out.println("========== Current Crop Information: ==========" +
                "\n[Sprouting > Seeding > Vegetative > Reproductive > Ripening]\n");
        boolean disease = false;
        for (Crop crop : crops) {
            System.out.println(crop);
            if (!crop.getCropHealth().equals("Healthy"))
                disease = true;
        }
        System.out.println("\n");
        if (disease)
            System.out.println("Disease detected in one or more crops, pest control recommended...\n");
    }

    /* Getter - Crop health (also used by DecisionMakerModule)*/
    public abstract String getCropHealth();

    /* Used by DecisionMakingModule - makePestControlDecision()
    * ... resets individual crop health to Healthy */
    public abstract void applyPestControl();

    public abstract void chanceToGainDisease();

    /* Checks current growth stage, advances it and causes a chance to gain disease, otherwise harvests crop if fully grown*/
    public void fertilize() {
        /* PSEUDOCODE:
        - assumed crop is healthy
        - advances growth stage of crop
        - if crop growth stage is maximum then harvest & reset growth stage
        - modify current health to chance of catching disease*/
        if (currentStage < 4) {
            growthStage = stages[++currentStage];
            this.chanceToGainDisease();
        }
        else
        {
            // crop is fully grown, harvest & reset
            this.harvestCrop();
        }
    }

    /* Setter - resets crop stage to '0' and updates yield information */
    private void harvestCrop() {
        currentStage = 0;
        growthStage = stages[currentStage];
        System.out.println("\n" + cropType + " has been harvested...\n");
        switch (cropType){
            case "Wheat":
                currentYield[0]++;
                break;
            case "Corn":
                currentYield[1]++;
                break;
            case "Tomatoes":
                currentYield[2]++;
                break;
        }
    }

    // get current yield (amt harvested) for [Wheat,Corn,Tomatoes]
    public static void displayYield() {
        System.out.println("========== Current Harvest: ==========" +
                "\nWheat: " + currentYield[0] + " bushels" +
                "\nCorn: " + currentYield[1] + " bushels" +
                "\nTomatoes: " + currentYield[2] + " bushels\n");
    }

    /* To be run in beginning of program - fills an ArrayList<Livestock> with user-decided Crops (Wheat,Corn,Tomatoes)*/
    public static void initializeCrops(ArrayList<Crop> crops) {
        System.out.println("========== Initialize Crop Info: ==========");

        char addAnother = 'y';
        int cropNum = 0;
        String cropType;
        do {
            System.out.println("Current number of crops: "+ cropNum);
            System.out.print("Add another to the system? (y/n): ");
            addAnother = input.next().toLowerCase().charAt(0);

            if (addAnother == 'y') {
                System.out.print("[Wheat/Corn/Tomatoes]: ");
                cropType = input.next().toLowerCase();
                switch (cropType) {
                    case "wheat":
                        crops.add(new Wheat());
                        cropNum++;
                        break;
                    case "corn":
                        crops.add(new Corn());
                        cropNum++;
                        break;
                    case "tomatoes":
                        crops.add(new Tomatoes());
                        cropNum++;
                        break;
                    default:
                        System.out.println(cropType + " not recognized...");
                }
            }
        } while ( addAnother == 'y' );
    }


}
