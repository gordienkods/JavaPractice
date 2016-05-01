package average.weather.getter.entity;

public class WeatherData {

    private String temperature;
    private String time;
    private String actualCity;
    private String expectedCity;

    public WeatherData(String expectedCity){
        this.expectedCity = expectedCity;
    }

    public String getExpectedCity() {
        return expectedCity;
    }

    public void setExpectedCity(String expectedCity) {
        this.expectedCity = expectedCity;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setActualCity(String actualCity) {
        this.actualCity = actualCity;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getTime() {
        return time;
    }

    public String getActualCity() {
        return actualCity;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temperature='" + temperature + '\'' +
                ", time='" + time + '\'' +
                ", actualCity='" + actualCity + '\'' +
                '}';
    }
}
