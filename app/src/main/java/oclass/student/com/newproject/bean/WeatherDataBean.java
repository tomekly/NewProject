package oclass.student.com.newproject.bean;

import java.io.Serializable;
import java.util.List;

class WeatherDataBean implements Serializable {

    private String shidu;
    private Integer pm25;
    private Integer pm10;
    private String quality;
    private Integer wendu;
    private String ganmao;
    private YesterdayBean yesterday;
    private List<ForecastBean> forecast;

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public Integer getPm25() {
        return pm25;
    }

    public void setPm25(Integer pm25) {
        this.pm25 = pm25;
    }

    public Integer getPm10() {
        return pm10;
    }

    public void setPm10(Integer pm10) {
        this.pm10 = pm10;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Integer getWendu() {
        return wendu;
    }

    public void setWendu(Integer wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public YesterdayBean getYesterday() {
        return yesterday;
    }

    public void setYesterday(YesterdayBean yesterday) {
        this.yesterday = yesterday;
    }

    public List<ForecastBean> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastBean> forecast) {
        this.forecast = forecast;
    }
}
