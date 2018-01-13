package com.dh.dhweather.ui.fragment.forecast;


import com.dh.dhweather.beans.Weather;
import com.dh.dhweather.beans.WeatherForecast;
import com.dh.dhweather.ui.base.BaseFragment;
import com.dh.dhweather.ui.fragment.current.CurrentFragmentContract;

import java.util.List;

public class ForecastFragment extends BaseFragment implements ForecastFragmentContract.View {
    @Override
    public void updateData(List<WeatherForecast> weatherForecast) {

    }

    @Override
    public void showMessage(String msg) {

    }
}
