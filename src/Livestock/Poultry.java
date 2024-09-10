package Livestock;

public class Poultry extends Livestock{

    /* Specific Livestock Information*/
    private int weight; // lbs (4-7)
    private int age; // 5-10 yrs

    public Poultry(String name) {
        super("Poultry",name);
        this.weight = 4 + (int)(Math.random() * 4);
        this.age = 5 + (int)(Math.random() * 6);
    }

    @Override
    public void feedAnimal() {
        // feed chicken 1/4 lb of feed per day
        System.out.println("Feeding " + super.getName() + " feed (" + " 0.25 lbs)...");
        if (this.chanceToYield()) {
            System.out.println("... produced eggs");
            currentYield[1]++;
        }
    }

    @Override
    public String toString() {
        return(super.toString() +
                " - Weight: " + weight + " lbs" +
                " - Age: " + age + " years");
    }


}
