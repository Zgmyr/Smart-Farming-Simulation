package Crops;

public class Tomatoes extends Crop {
    /* Crop Health for Tomatoes */
    private final String[] healthConditions = {"Healthy","Early Blight","Septoria Leaf Spot"};
    private String health; // holds current health
    private int susceptibility; // index for healthConditions

    public Tomatoes() {
        super("Tomatoes");
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
