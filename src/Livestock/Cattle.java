package Livestock;

import java.util.ArrayList;

public class Cattle extends Livestock{

    /* Specific Livestock Information*/
    private int weight; // lbs (800 - 1400)
    private int age; // 20-30 yrs

    public Cattle(String name) {
        super("Cattle",name);
        this.weight = 800 + (int)(Math.random() * 600);
        this.age = 20 + (int)(Math.random() * 11);
    }

    @Override
    public void feedAnimal() {
        // feed cow 2.0% of body weight in hay per day
        System.out.println("Feeding " + super.getName() + " hay (" + (weight * 0.02) + " lbs)...");
        if (this.chanceToYield()) {
            System.out.println("... produced milk");
            currentYield[0]++;
        }
    }

    @Override
    public String toString() {
        return(super.toString() +
                " - Weight: " + weight + " lbs" +
                " - Age: " + age + " years");
    }






    // UNIT TESTING - Ignore this...
    public static void main(String[] args) {
        ArrayList<Livestock> livestock = new ArrayList<>();

        initializeLivestock(livestock);
        System.out.println("INIITAL\n" + livestock);

        System.out.println(livestock.getFirst());

        System.out.println("\n\nDEBUG VITALS:\n");

        trackLivestockVitals(livestock);

        administerMedication(livestock);

        System.out.println("\nAFTER\n" + livestock);




    }
}
