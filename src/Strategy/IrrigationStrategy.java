package Strategy;

import DecisionMaker.DecisionMaker;

import java.util.Scanner;

public abstract class IrrigationStrategy {
    Scanner input = new Scanner(System.in);
    public final static double irrigationRate = 1; // gal/min (system specifications)

    abstract void viewCurrentIrrigationStrategy();

    public abstract double getIrrigationDuration();

    public abstract int getIrrigationFrequency();

}
