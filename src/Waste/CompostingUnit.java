package Waste;

public class CompostingUnit extends WasteManagement {

    public CompostingUnit() {
        super("Composting Unit");
    }

    /* Empty the CompostingUnit by setting %full to '0' */
    @Override
    public void manageWaste() {
        this.empty();
        System.out.println("Composting Unit has been emptied...\n");
    }

    // CompostingUnit does not recycle, this is done by RecyclingFacility
    @Override
    public void recycleMaterials() { }
}
