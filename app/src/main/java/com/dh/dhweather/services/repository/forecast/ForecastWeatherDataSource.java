package com.dh.dhweather.services.repository.forecast;


import com.dh.dhweather.services.pojo.ForecastWeather;

import io.reactivex.Observable;

public interface ForecastWeatherDataSource {
    Observable<ForecastWeather> getForecastWeather(String locationName, String units, String mode, String count, String appId);
}
