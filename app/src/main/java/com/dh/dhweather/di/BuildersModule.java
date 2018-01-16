package com.dh.dhweather.di;

import com.dh.dhweather.ui.fragment.WeatherFragment;
import com.dh.dhweather.ui.fragment.WeatherFragmentModule;
import com.dh.dhweather.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector()
    abstract MainActivity bindWeatherActivity1();

    @ContributesAndroidInjector(modules = WeatherFragmentModule.class)
    abstract WeatherFragment bindWeatherFragment();

}
