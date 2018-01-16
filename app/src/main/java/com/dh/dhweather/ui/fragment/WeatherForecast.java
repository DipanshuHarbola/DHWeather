package com.dh.dhweather.ui.fragment;


public class WeatherForecast {
    private long day;
    private Integer iconId;
    private String description;
    private Integer maxTemp;
    private Integer minTemp;

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Integer maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Integer getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Integer minTemp) {
        this.minTemp = minTemp;
    }
}
