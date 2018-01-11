package com.dh.dhweather.services.repository.current;

import com.dh.dhweather.beans.Weather;
import com.dh.dhweather.services.api.ApiService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class CurrentWeatherData implements CurrentWeatherDataSource {

    private ApiService apiService;

    @Inject
    public CurrentWeatherData(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<Weather> getCurrentWeather(String locationName) {
        return apiService.getCurrentWeather(locationName);
    }
}
