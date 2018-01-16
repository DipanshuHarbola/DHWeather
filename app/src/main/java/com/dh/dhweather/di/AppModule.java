package com.dh.dhweather.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;

import com.dh.dhweather.WeatherApp;
import com.dh.dhweather.services.api.ApiClient;
import com.dh.dhweather.services.api.ApiService;
import com.dh.dhweather.services.repository.current.CurrentWeatherData;
import com.dh.dhweather.services.repository.forecast.ForecastWeatherData;
import com.dh.dhweather.utils.NetworkUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    Context provideContext(WeatherApp application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    Typeface provideTypeface(Context context){
        return Typeface.createFromAsset(context.getAssets(),"fonts/weathericons.ttf");
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    NetworkUtil provideNetworkUtil(Context context) {
        return new NetworkUtil(context);
    }

    @Singleton
    @Provides
    ApiService provideApiService(WeatherApp application) {
        return ApiClient.getOpenWeatherMapService(application);
    }

    @Singleton
    @Provides
    CurrentWeatherData provideCurrentWeatherData(ApiService apiService) {
        return new CurrentWeatherData(apiService);
    }

    @Singleton
    @Provides
    ForecastWeatherData provideForecastWeatherData(ApiService apiService) {
        return new ForecastWeatherData(apiService);
    }

}
