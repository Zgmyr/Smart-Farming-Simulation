package Livestock;

public class Sheep extends Livestock{

    /* Specific Livestock Information*/
    private int weight; // lbs (100-350)
    private int age; // 10-12 yrs

    Sheep(String name) {
        super("Sheep",name);
        this.weight = 100 + (int)(Math.random() * 251);
        this.age = 10 + (int)(Math.random() * 3);
    }

    @Override
    public void feedAnimal() {
        // feed sheep 2.0% of body weight in grain per day
        System.out.println("Feeding " + super.getName() + " grain (" + (weight * 0.02) + " lbs)...");
        if (this.chanceToYield()) {
            System.out.println("... produced wool");
            currentYield[2]++;
        }
    }

    @Override
    public String toString() {
        return(super.toString() +
                " - Weight: " + weight + " lbs" +
                " - Age: " + age + " years");
    }

}
