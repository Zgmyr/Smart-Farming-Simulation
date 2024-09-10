package Sensors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WeatherSensor implements Sensor {
    static ArrayList<String> conditions = new ArrayList<>(Arrays.asList("sunny","rainy","cloudy"));
    Scanner input = new Scanner(System.in);

    // USER INPUT - returns weather condition from predefined ArrayList
    @Override
    public String readWeatherCondition() {


        boolean invalidCondition = true;
        String weatherCondition = null;

        do {
            System.out.print("[weather condition] (sunny/rainy/cloudy): ");
            weatherCondition = input.next().toLowerCase();
            if (conditions.contains(weatherCondition))
                invalidCondition = false;
        } while (invalidCondition);
        return weatherCondition;
    }

    public static String readWeatherForecast() {
        int randIndex = (int)(Math.random() * 2);
        return conditions.get(randIndex);
    }




    // use SoilMoistureSensor to read moisture level
    @Override
    public double readMoistureLevel() {
        return -1;
    }
}
