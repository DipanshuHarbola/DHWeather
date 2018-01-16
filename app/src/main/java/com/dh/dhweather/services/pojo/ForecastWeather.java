package com.dh.dhweather.services.pojo;


import java.util.List;

public class ForecastWeather {

    private List<WeatherList> list = null;

    public List<WeatherList> getList() {
        return list;
    }

    public void setList(List<WeatherList> list) {
        this.list = list;
    }


    public class WeatherList {

        private Long dt;
        private Temp temp;
        private List<Weather> weather = null;

        public Long getDt() {
            return dt;
        }

        public void setDt(Long dt) {
            this.dt = dt;
        }

        public Temp getTemp() {
            return temp;
        }

        public void setTemp(Temp temp) {
            this.temp = temp;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }

    }


    public class Temp {

        private Double min;
        private Double max;

        public Double getMin() {
            return min;
        }

        public void setMin(Double min) {
            this.min = min;
        }

        public Double getMax() {
            return max;
        }

        public void setMax(Double max) {
            this.max = max;
        }

    }


    public class Weather {

        private Integer id;
        private String main;
        private String description;
        private String icon;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

    }

}
