package CarbonEmissions;

public class CarbonFootprintTracking implements CarbonFootprint {

    private int methaneEmissionScore = 0;
    private int nitrousOxideEmissionScore = 0;
    private int carbonDioxideEmissionScore = 0;

    /* Display emission scores for each category */
    @Override
    public void trackEmissions() {
        System.out.println("Methane Emission Score: " + methaneEmissionScore +
                "\nNitrous Oxide Emission Score: " + nitrousOxideEmissionScore +
                "\nCarbon Dioxide Emission Score: " + carbonDioxideEmissionScore);
    }

    /* passed in an integer (1,2,3) increments score of corresponding emission category */
    public void increase(int type) {
        switch (type) {
            case 1:
                this.methaneIncrease();
                break;
            case 2:
                this.nitrousOxideIncrease();
                break;
            case 3:
                this.carbonDioxideIncrease();
                break;
        }
    }

    /* incrementor for Methane score */
    private void methaneIncrease() {
        methaneEmissionScore++;
    }

    /* incrementor for Nitrous Oxide score */
    private void nitrousOxideIncrease() {
        nitrousOxideEmissionScore++;
    }

    /* incrementor for Carbon Dioxide score */
    private void carbonDioxideIncrease() {
        carbonDioxideEmissionScore++;
    }

    /* Setter - updates emissions scores using constant from CarbonFootprint interface */
    public void setScores() {
        this.methaneEmissionScore = (int)(methaneEmissionScore * emissionReductionEffectiveness);
        this.nitrousOxideEmissionScore = (int)(methaneEmissionScore * emissionReductionEffectiveness);
        this.carbonDioxideEmissionScore = (int)(methaneEmissionScore * emissionReductionEffectiveness);
    }







    /* Not implemented here, check CarbonFootprintReduction */
    @Override
    public void reduceEmissions(CarbonFootprintTracking carbonFootprint) {

    }
}
