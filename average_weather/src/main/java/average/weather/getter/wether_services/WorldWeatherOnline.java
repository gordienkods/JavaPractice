package average.weather.getter.wether_services;

import average.weather.getter.entity.WeatherData;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;

public class WorldWeatherOnline implements Callable<WeatherData> {

    private WeatherData weather;
    private String response;

    public WorldWeatherOnline (WeatherData weatherData){
        this.weather = weatherData;
    }

    private void sendRequest (){
        String API_REQUEST = "http://api.worldweatheronline.com/premium/v1/weather.ashx?" +
        "key=654a1cfc175a418fa43182629162804&" +
        "q=" + weather.getExpectedCity()+ "&" +
        "format=json&" +
        "num_of_days=5";

        response = HttpProvider.sendApiRequest(API_REQUEST);
    }

    public WeatherData getDataFromService(){
        sendRequest();
        parseResponse();
        return weather;
    }

    public WeatherData call(){
        sendRequest();
        parseResponse();
        return weather;
    }

    private void parseResponse(){

        JSONObject json = new JSONObject(response);
        String time = json.getJSONObject("data").getJSONArray("current_condition").getJSONObject(0).get("observation_time").toString();
        String temp = json.getJSONObject("data").getJSONArray("current_condition").getJSONObject(0).get("temp_C").toString();
        String city = json.getJSONObject("data").getJSONArray("request").getJSONObject(0).get("query").toString();

        weather.setTime(time);
        weather.setTemperature(new BigDecimal(temp).setScale(0, RoundingMode.HALF_UP).toString());
        weather.setActualCity(city);
    }

    public WeatherData getWeatherDatal(String city){
        return weather;
    }
}
