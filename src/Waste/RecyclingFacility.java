package Waste;

public class RecyclingFacility extends WasteManagement{

    public RecyclingFacility() {
        super("Recycling Facility");
    }

    /* Empty the RecyclingFacility by setting %full to '0' */
    @Override
    public void recycleMaterials() {
        this.empty();
        System.out.println("Recycling Facility has been emptied...\n");
    }

    /* Recycling Facility does not manage waste, this is done by CompostingUnit */
    @Override
    public void manageWaste() { }
}
