package CarbonEmissions;

public interface CarbonFootprint {

    double emissionReductionEffectiveness = 0.25;

    public void trackEmissions();
    public void reduceEmissions(CarbonFootprintTracking carbonFootprint);

}
