package com.dh.dhweather.services.repository.current;


import com.dh.dhweather.beans.Weather;

import io.reactivex.Observable;

public interface CurrentWeatherDataSource {
    Observable<Weather> getCurrentWeather(String locationName);
}
