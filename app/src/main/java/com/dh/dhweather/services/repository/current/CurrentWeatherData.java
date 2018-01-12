package com.dh.dhweather.services.repository.current;

import com.dh.dhweather.beans.Weather;
import com.dh.dhweather.services.api.ApiService;
import com.dh.dhweather.services.pojo.CurrentWeather;

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
    public Observable<CurrentWeather> getCurrentWeather(String locationName, String units, String appId) {
        return apiService.getCurrentWeather(locationName, units, appId);
    }
}
