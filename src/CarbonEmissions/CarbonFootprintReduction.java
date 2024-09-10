package CarbonEmissions;

public class CarbonFootprintReduction implements CarbonFootprint {

    /* Uses the setter method setScores() from CarbonFootprintTracking to reduce emissions from each category */
    @Override
    public void reduceEmissions(CarbonFootprintTracking carbonFootprint) {
        carbonFootprint.setScores();
    }

    /* Not implemented here, see CarbonFootprintTracking */
    @Override
    public void trackEmissions() {

    }
}
