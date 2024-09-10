package Livestock;

import SmartIrrigationSystem.SmartIrrigationSystem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Livestock {
    static Scanner input = new Scanner(System.in);
    static int numLivestock = 0;
    public String[] vitalConditions = {"Good","Normal","Poor"};

    /* Generic Livestock Information: */
    private final String name;
    private final String type;
    private String vitals;

    /* Production Information: */
    private int productionValue; // ranking 25-100... higher is better chance, individual to each livestock
    static int[] currentYield = {0,0,0}; // units {milk,eggs,wool}

    public Livestock(String type, String name) {
        this.type = type;
        this.name = name;
        this.vitals = vitalConditions[(int)(Math.random() * 3)];
        this.productionValue = 25 + (int)(Math.random() * 76);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return("(" + type + ")" + " Name: " + name +
                " - Vitals: " + vitals);
    }

    /* Each animal has its own feed requirements */
    public abstract void feedAnimal();

    /* Chance to produce positive yield dependent on production value of individual animal*/
    public boolean chanceToYield() {
        int yieldChance = (int)(Math.random() * 101);
        return yieldChance < productionValue; // true if rand generated (yieldChance) < productionValue (individual)
    }

    /* Feeds livestock, has chance to increment production yield*/
    public static void feedLivestock(ArrayList<Livestock> livestock, SmartIrrigationSystem smartSystem) {
        System.out.println("========== Dietary Report: ==========");
        if (livestock.isEmpty()) {
            System.out.println("There are no livestock to feed!\n");
            return;
        }

        // Ensure Waste Management facilities are empty before feeding
        if (smartSystem.composter.isFull()) {
            System.out.println("WARNING: The composting unit is full and requires attention before feeding!\n");
            return;
        }
        else if (smartSystem.recycling.isFull()) {
            System.out.println("WARNING: The recycling facility is full and requires attention before feeding!\n");
            return;
        }

        for (Livestock animal : livestock)
            animal.feedAnimal();

        // add to waste some amount (2-15%)
        smartSystem.composter.addWasteOrRecyclables(smartSystem);
        smartSystem.recycling.addWasteOrRecyclables(smartSystem);

        // increase methane emissions
        smartSystem.increaseEmissions(1);

        System.out.println("\nAll animals fed...\n");
    }

    // get current production yield for [Milk, eggs, wool]
    public static void displayYield() {
        System.out.println("========== Current Production Yield: ==========" +
                "\nMilk: " + currentYield[0] + " gal" +
                "\nEggs: " + currentYield[1] + " eggs" +
                "\nWool: " + currentYield[2] + " bale\n");
    }

    /* Read Vitals for all Livestock */
    public static void trackLivestockVitals(ArrayList<Livestock> livestock) {
        System.out.println("========== Livestock Vitals: ==========");
        for (Livestock animal : livestock)
            animal.readVitals();
        if (recommendActions(livestock))
            System.out.println("\nPoor vitals detected, recommended administer medication...\n");
        else
            System.out.println("\n");
    }
    /* Read Vitals for a singular Livestock */
    private void readVitals() {
        if (!vitals.equals("Poor"))
            vitals = vitalConditions[(int)(Math.random() * 3)];
        System.out.println(this);
    }
    /* One way flag - True if one Livestock has Poor vitals */
    private static boolean recommendActions(ArrayList<Livestock> livestock) {
        for (Livestock animal : livestock) {
            if (animal.vitals.equals("Poor"))
                return true;
        }
        return false;
    }
    /* Treats any Livestock that have Poor vitals */
    public static void administerMedication(ArrayList<Livestock> livestock) {
        System.out.println("========== Administer Medication: ==========");
        if (livestock.isEmpty()) {
            System.out.println("There are no livestock to administer medicine to!\n");
        }
        else {
            ArrayList<Livestock> poorVitals = new ArrayList<>();
            boolean poorVitalsDetected = false;
            for (Livestock animal : livestock) {
                if (animal.vitals.equals("Poor")) {
                    poorVitals.add(animal);
                    poorVitalsDetected = true;
                }
            }

            if (poorVitalsDetected) {
                System.out.println("The following livestock will be administered treatment:");
                for (Livestock animal : poorVitals) {
                    System.out.println(animal);
                    animal.vitals = "Good";
                }
                System.out.println("\nMedicine administered...\n");
            } else
                System.out.println("No need to administer medicine, all livestock in good health!\n");
        }
    }

    /* To be run in beginning of program - fills an ArrayList<Livestock> with user-decided Livestock (Cattle,Poultry,Sheep)*/
    public static void initializeLivestock(ArrayList<Livestock> livestock) {
        System.out.println("========== Initialize Livestock Info: ==========");

        char addAnother = 'y';
        String livestockType;
        String name;
        do {
            System.out.println("Current livestock: "+ numLivestock);
            System.out.print("Add another to the system? (y/n): ");
            addAnother = input.next().toLowerCase().charAt(0);

            if (addAnother == 'y') {
                System.out.print("[Cattle/Poultry/Sheep]: ");
                livestockType = input.next().toLowerCase();
                switch (livestockType) {
                    case "cattle":
                        System.out.print("Enter livestock name: ");
                        livestock.add( new Cattle(input.next()) );
                        numLivestock++;
                        break;
                    case "poultry":
                        System.out.print("Enter livestock name: ");
                        livestock.add( new Poultry(input.next()) );
                        numLivestock++;
                        break;
                    case "sheep":
                        System.out.print("Enter livestock name: ");
                        livestock.add( new Sheep(input.next()) );
                        numLivestock++;
                        break;
                    default:
                        System.out.println(livestockType + " not recognized...");
                }
            }
        } while ( addAnother == 'y' );
    }

    /* Shows current livestock */
    public static void viewListOfLivestock(ArrayList<Livestock> livestock) {
        System.out.println("========== Current Livestock Records: ==========");
        for (Livestock animal : livestock)
            System.out.println(animal);
        System.out.println("\n");
    }





}
