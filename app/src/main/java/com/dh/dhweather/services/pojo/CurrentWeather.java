package com.dh.dhweather.services.pojo;


import android.graphics.Typeface;

import java.util.List;

public class CurrentWeather {

    private List<Weather> weather = null;
    private Main main;
    private Long dt;
    private Sys sys;
    private Wind wind;
    private String name;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        @Override
        public String toString() {
            return "Weather{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    public class Main {
        private Double temp;
        private Integer pressure;
        private Integer humidity;

        public Double getTemp() {
            return temp;
        }

        public void setTemp(Double temp) {
            this.temp = temp;
        }

        public Integer getPressure() {
            return pressure;
        }

        public void setPressure(Integer pressure) {
            this.pressure = pressure;
        }

        public Integer getHumidity() {
            return humidity;
        }

        public void setHumidity(Integer humidity) {
            this.humidity = humidity;
        }


        @Override
        public String toString() {
            return "Main{" +
                    "temp=" + temp +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    '}';
        }
    }

    public class Sys {

        private String country;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return "Sys{country=" + country + "}";
        }
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "weather=" + weather +
                ", main=" + main +
                ", dt=" + dt +
                ", sys=" + sys +
                ", name='" + name + '\'' +
                '}';
    }

    public class Wind {

        private Double speed;

        public Double getSpeed() {
            return speed;
        }

        public void setSpeed(Double speed) {
            this.speed = speed;
        }


    }

}
