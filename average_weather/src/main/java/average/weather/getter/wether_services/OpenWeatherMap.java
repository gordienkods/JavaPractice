package average.weather.getter.wether_services;

import average.weather.getter.entity.WeatherData;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Callable;

public class OpenWeatherMap implements Callable<WeatherData> {

    private WeatherData weatherData;
    private String response;

    public OpenWeatherMap (WeatherData weatherData){
        this.weatherData = weatherData;
    }

    private void sendRequest (){
        String API_REQUEST = "http://api.openweathermap.org/data/2.5/weather?q="+ weatherData.getExpectedCity() +"&APPID=f1839445bca3117bc75d8de7b512de2c";
        response = HttpProvider.sendApiRequest(API_REQUEST);
    }

    private void parseResponse(){
        JSONObject json = new JSONObject(response);
        String time = "undefined";
        String temp = json.getJSONObject("main").get("temp").toString();
        String city = json.get("name").toString();

        weatherData.setTime(time);
        weatherData.setTemperature(new BigDecimal(celvinToCels(temp)).setScale(0, RoundingMode.HALF_UP).toString());
        weatherData.setActualCity(city);
    }

    public WeatherData getDataFromService(){
        sendRequest();
        parseResponse();
        return weatherData;
    }

    public WeatherData call(){
        sendRequest();
        parseResponse();
        return weatherData;
    }

    private String celvinToCels (String celvinTempValue){
        return String.valueOf(Float.parseFloat(celvinTempValue) - 273);
    }
}
