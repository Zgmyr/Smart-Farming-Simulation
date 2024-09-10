package Crops;

import java.util.ArrayList;

public class Wheat extends Crop {
    /* Crop Health for Wheat */
    private final String[] healthConditions = {"Healthy","Powdery Mildew","Stripe Rust"};
    private String health; // holds current health
    private int susceptibility; // index for healthConditions
    public Wheat() {
        super("Wheat");
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
