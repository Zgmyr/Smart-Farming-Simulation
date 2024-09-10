package Crops;

public class Corn extends Crop {

    /* Crop Health for Corn */
    private final String[] healthConditions = {"Healthy","Common Rust","Gray Leaf Spot"};
    private String health; // holds current health
    private int susceptibility; // index for healthConditions

    public Corn() {
        super("Corn");
        this.susceptibility = (int)(Math.random() * 3);
        this.health = healthConditions[susceptibility];
    }

    @Override
    public String toString() {
        return(super.toString() + " - Crop Health: " + health);
    }

    @Override
    public String getCropHealth() {
        return health;
    }

    @Override
    public void applyPestControl() {
        this.susceptibility = 0;
        this.health = healthConditions[susceptibility];
    }

    @Override
    public void chanceToGainDisease() {
        this.susceptibility = (int)(Math.random() * 3);
        this.health = healthConditions[susceptibility];
    }

}
