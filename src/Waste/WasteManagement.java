package Waste;

import SmartIrrigationSystem.SmartIrrigationSystem;

public abstract class WasteManagement {
    private double percentFull = 0; // up to 100% & may include overflow
    private final String type; // "Composting Unit" or "Recycling Facility"

    public WasteManagement(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return(type + " - " + percentFull + " % full");
    }

    public static void viewWasteManagementStatus(WasteManagement facility1, WasteManagement facility2) {
        System.out.println("========== Waste Management Report: ==========\n" +
                facility1 + "\n" +
                facility2 + "\n");
    }

    /* Checks if facility is full */
    public boolean isFull() {
        return (this.percentFull >= 100); // true if full
    }

    /* increment %full - waste/recyclables added with:
    * - fertilization
    * - pest control
    * - feeding livestock
    * ... also interacts with the emissionsTracker from SmartIrrigationSystem to increase carbon dioxide emissions
    *  when composter becomes full*/
    public void addWasteOrRecyclables(SmartIrrigationSystem smartSystem) {
        percentFull += (Math.random() * 13) + 2; // increase %full by 2-15% (random)

        // alert user if composter/facility is full
        if (this.percentFull > 100) {
            System.out.println("WARNING: " + this.type + " is over capacity (" + percentFull + "%)");
            if (this.type.equals("Composting Unit"))
                smartSystem.increaseEmissions(3); // increase carbon dioxide emissions
        }
    }

    void empty() {
        percentFull = 0;
    }

    public abstract void manageWaste();

    public abstract void recycleMaterials();

}
