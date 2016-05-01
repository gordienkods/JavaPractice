package average.weather.getter;

import average.weather.getter.entity.WeatherData;
import average.weather.getter.wether_services.OpenWeatherMap;
import average.weather.getter.wether_services.WorldWeatherOnline;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) {

        WeatherData worldWeather  = new WeatherData("Dnipropetrovsk");
        WeatherData openWeatherMap = new WeatherData("Dnipropetrovsk");
        WorldWeatherOnline worldWeatherOnlineService = new WorldWeatherOnline(worldWeather);
        OpenWeatherMap openWeatherMapService = new OpenWeatherMap(openWeatherMap);

        ExecutorService pool = Executors.newFixedThreadPool(2);
        Collection<Future<WeatherData>> futures = new HashSet<Future<WeatherData>>();

        futures.add(pool.submit(worldWeatherOnlineService));
        futures.add(pool.submit(openWeatherMapService));

        pool.shutdown();

        try {
            for (Future<WeatherData> weatherDataFuture : futures) {
                System.out.println(weatherDataFuture.get().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("AVERAGE TEMP: " + getAverageTemp(futures));
    }

    private static Integer getAverageTemp (Collection<Future<WeatherData>> futures){
        Collection<WeatherData> weatherDatas = new ArrayList<WeatherData>();
        Integer summ = 0;
        try {
            for (Future<WeatherData> weatherDataFuture : futures) {
                weatherDatas.add(weatherDataFuture.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (WeatherData data : weatherDatas){
            summ = summ + Integer.parseInt(data.getTemperature());
        }
        return summ/futures.size();
    }

}
