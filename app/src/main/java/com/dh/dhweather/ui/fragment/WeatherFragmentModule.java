package com.dh.dhweather.ui.fragment;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WeatherFragmentModule {

    @Binds
    abstract WeatherFragmentContract.View view(WeatherFragment weatherFragment);

    @Binds
    abstract WeatherFragmentContract.Presenter presenter(ImpWeatherFragmentPresenter fragmentPresenter);
}
