package com.dh.dhweather.utils;

import com.dh.dhweather.ui.fragment.WeatherForecast;
import com.dh.dhweather.services.pojo.ForecastWeather;

import java.util.ArrayList;
import java.util.List;


public class WeatherDataSet {
    // Weather Forecast Data
    public static List<WeatherForecast> getWeatherForecastData(ForecastWeather forecastWeather){
        List<WeatherForecast> forecastList = new ArrayList<>();
        for (int i =1; i<forecastWeather.getList().size(); i++){
            ForecastWeather.WeatherList list = forecastWeather.getList().get(i);

            WeatherForecast weatherForecast = new WeatherForecast();
            weatherForecast.setDay(list.getDt());

            ForecastWeather.Temp temp = list.getTemp();
            weatherForecast.setMaxTemp(temp.getMax().intValue());
            weatherForecast.setMinTemp(temp.getMin().intValue());

            ForecastWeather.Weather details = list.getWeather().get(0);
            weatherForecast.setDescription(details.getDescription());
            weatherForecast.setIconId(details.getId());
            forecastList.add(weatherForecast);

        }

        return forecastList;
    }
}
