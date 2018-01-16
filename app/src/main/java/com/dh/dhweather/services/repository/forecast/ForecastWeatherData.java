package com.dh.dhweather.services.repository.forecast;

import com.dh.dhweather.services.api.ApiService;
import com.dh.dhweather.services.pojo.ForecastWeather;

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
    public Observable<ForecastWeather> getForecastWeather(String locationName, String units, String mode, String count, String appId) {
        return apiService.getForecastWeather(locationName, units, mode, count, appId);
    }
}
