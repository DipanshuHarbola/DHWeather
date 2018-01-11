package com.dh.dhweather.services.api;


import com.dh.dhweather.beans.Weather;
import com.dh.dhweather.beans.WeatherForecast;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("users/{user}/repos")
    Observable<Weather> getCurrentWeather(@Path("user") String userName);

    @GET("users/{user}/repos")
    Observable<WeatherForecast> getForecastWeather(@Path("user") String userName);
}
