import io.tutorial.weather.WeatherFetcher;
import io.tutorial.weather.WeatherInfo;

import java.util.Scanner;

public class WeatherAPI {

    public static void main(String[] args) throws Exception {
        WeatherFetcher weatherFetcher = WeatherFetcher.getInstance();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Für welche Stadt soll das Wetter abgefragt werden?");
        String city = scanner.nextLine();

        WeatherInfo[] weatherInfos = weatherFetcher.fetch(city);

        for (int x = 0; x < weatherInfos.length; x++) {
            WeatherInfo weatherInfo = weatherInfos[x];
            System.out.println(weatherInfo.getTimestamp() + ": " + weatherInfo.getTemperature());
        }
    }
}
