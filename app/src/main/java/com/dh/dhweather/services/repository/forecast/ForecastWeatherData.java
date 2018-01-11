package com.dh.dhweather.services.repository.forecast;

import com.dh.dhweather.beans.Weather;
import com.dh.dhweather.beans.WeatherForecast;
import com.dh.dhweather.services.api.ApiService;
import com.dh.dhweather.services.repository.current.CurrentWeatherDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class ForecastWeatherData implements ForecastWeatherDataSource {

    private ApiService apiService;

    @Inject
    public ForecastWeatherData(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<WeatherForecast> getForecastWeather(String locationName) {
        return apiService.getForecastWeather(locationName);
    }
}
