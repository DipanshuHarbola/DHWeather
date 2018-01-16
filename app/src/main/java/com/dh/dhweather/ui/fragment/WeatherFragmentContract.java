package com.dh.dhweather.ui.fragment;


import com.dh.dhweather.services.pojo.CurrentWeather;
import com.dh.dhweather.services.pojo.ForecastWeather;
import com.dh.dhweather.ui.base.BasePresenter;
import com.dh.dhweather.ui.base.BaseView;

import java.util.List;

public class WeatherFragmentContract {

    public interface View extends BaseView {

        void updateLocation(String locationName);

        void updateCurrentWeatherData(CurrentWeather currentWeather);

        void updateForecastWeatherData(List<WeatherForecast> forecastWeather);

        void showMessage(String msg);

    }

    public interface Presenter extends BasePresenter {

        void location(String locationName);

        void loadCurrentWeatherData();

        void loadForecastWeatherData();

    }

}
