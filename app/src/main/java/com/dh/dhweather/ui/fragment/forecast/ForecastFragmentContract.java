package com.dh.dhweather.ui.fragment.forecast;


import com.dh.dhweather.beans.Weather;
import com.dh.dhweather.beans.WeatherForecast;
import com.dh.dhweather.ui.base.BasePresenter;

public class ForecastFragmentContract {

    public interface View {

        void updateData(WeatherForecast weatherForecast);

        void showMessage(String msg);

    }

    public interface Presenter extends BasePresenter {

        void location(String locationName);

        void loadData();

    }

}
