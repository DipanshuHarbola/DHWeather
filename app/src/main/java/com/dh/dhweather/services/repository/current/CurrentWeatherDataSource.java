package com.dh.dhweather.services.repository.current;


import com.dh.dhweather.beans.Weather;
import com.dh.dhweather.services.pojo.CurrentWeather;

import io.reactivex.Observable;

public interface CurrentWeatherDataSource {
    Observable<CurrentWeather> getCurrentWeather(String locationName, String units, String appId);
}
