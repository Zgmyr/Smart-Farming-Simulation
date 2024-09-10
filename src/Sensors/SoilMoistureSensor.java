package Sensors;
import java.util.Scanner;

public class SoilMoistureSensor implements Sensor {

    Scanner input = new Scanner(System.in);

    // USER INPUT - returns moisture level percentage (%) 0-100
    @Override
    public double readMoistureLevel() {
//        Scanner input = new Scanner(System.in);

        System.out.print("[soil moisture %]: ");
        double moistureLevel = input.nextDouble();
        while (moistureLevel < 0 || moistureLevel > 100) {
            System.out.print("[soil moisture %]: ");
            moistureLevel = input.nextDouble();
        }
        return (moistureLevel);
    }
    // use WeatherSensor to read weather condition
    @Override
    public String readWeatherCondition() {
        return "N/A";
    }
}
