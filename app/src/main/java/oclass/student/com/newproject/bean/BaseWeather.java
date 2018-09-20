package oclass.student.com.newproject.bean;

import java.io.Serializable;
import java.util.List;

public class BaseWeather implements Serializable {
    private String date;
    private String message;
    private Integer status;
    private String city;
    private Integer count;
    private WeatherDataBean data;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public WeatherDataBean getWeatherData() {
        return data;
    }

    public void setWeatherData(WeatherDataBean weatherData) {
        this.data = weatherData;
    }
}
