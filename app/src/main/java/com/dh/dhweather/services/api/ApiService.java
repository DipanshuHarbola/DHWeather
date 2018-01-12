package com.dh.dhweather.services.api;


import com.dh.dhweather.services.pojo.CurrentWeather;
import com.dh.dhweather.services.pojo.ForecastWeather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("weather")
    Observable<CurrentWeather> getCurrentWeather(@Query("q") String locationName, @Query("units") String units, @Query("appid") String appid);

    @GET("forecast/daily")
    Observable<ForecastWeather> getForecastWeather(@Query("q") String locationName, @Query("units") String units, @Query("mode") String mode, @Query("cnt") String count, @Query("appid") String appid);
}
