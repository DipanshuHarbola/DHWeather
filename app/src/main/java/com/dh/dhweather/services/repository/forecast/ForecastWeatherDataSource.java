package com.dh.dhweather.services.repository.forecast;


import com.dh.dhweather.beans.Weather;
import com.dh.dhweather.beans.WeatherForecast;

import io.reactivex.Observable;

public interface ForecastWeatherDataSource {
    Observable<WeatherForecast> getForecastWeather(String locationName);
}
