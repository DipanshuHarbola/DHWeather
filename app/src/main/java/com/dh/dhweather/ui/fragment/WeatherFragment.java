package com.dh.dhweather.ui.fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dh.dhweather.R;
import com.dh.dhweather.services.pojo.CurrentWeather;
import com.dh.dhweather.ui.adapter.ForecastAdapter;
import com.dh.dhweather.ui.base.BaseFragment;
import com.dh.dhweather.utils.NetworkUtil;
import com.dh.dhweather.utils.WeatherIcons;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dh.dhweather.utils.Constants.KEY_DEFAULT_LOCATION;
import static com.dh.dhweather.utils.Constants.KEY_LOCATION;

public class WeatherFragment extends BaseFragment implements WeatherFragmentContract.View {

    @Inject
    WeatherFragmentContract.Presenter presenter;
    @Inject
    Typeface typeface;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    NetworkUtil network;

    @BindView(R.id.txt_city_country)
    TextView txtCityCountry;
    @BindView(R.id.txt_last_updated)
    TextView txtLastUpdated;
    @BindView(R.id.txt_image_font)
    TextView txtImageFont;
    @BindView(R.id.txt_temperature)
    TextView txtTemperature;
    @BindView(R.id.txt_temp_unit)
    TextView txtTempUnit;
    @BindView(R.id.txt_description)
    TextView txtDescription;
    @BindView(R.id.txt_img_wind_speed)
    TextView txtImgWindSpeed;
    @BindView(R.id.txt_img_humidity)
    TextView txtImgHumidity;
    @BindView(R.id.txt_img_pressure)
    TextView txtImgPressure;
    @BindView(R.id.txt_wind_speed)
    TextView txtWindSpeed;
    @BindView(R.id.txt_pressure)
    TextView txtPressure;
    @BindView(R.id.txt_humidity)
    TextView txtHumidity;
    @BindView(R.id.txt_sheet_title)
    TextView txtSheetTitle;
    @BindView(R.id.rv_forecast_list)
    RecyclerView rvForecastList;
    @BindView(R.id.bottom_sheet)
    ConstraintLayout bottomSheet;
    @BindView(R.id.fab_location)
    FloatingActionButton fabLocation;
    @BindView(R.id.layout_main)
    CoordinatorLayout layoutMain;
    Unbinder unbinder;

    private ForecastAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.subscribe();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvForecastList.setLayoutManager(linearLayoutManager);
        rvForecastList.setHasFixedSize(true);

        adapter = new ForecastAdapter(getContext(),typeface);
        rvForecastList.setAdapter(adapter);
        initBottomSheet();

        if (!network.isConnected()){
            showMessage(getString(R.string.no_internet));
        }
        else {
            updateLocation(sharedPreferences.getString(KEY_LOCATION, KEY_DEFAULT_LOCATION));
        }

        return rootView;
    }

    private void initBottomSheet() {
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (BottomSheetBehavior.STATE_DRAGGING == newState){
                    txtSheetTitle.animate().scaleX(0).scaleY(0).setDuration(300).start();
                    txtSheetTitle.setVisibility(View.GONE);
                }
                else if (BottomSheetBehavior.STATE_COLLAPSED == newState){
                    txtSheetTitle.setVisibility(View.VISIBLE);
                    txtSheetTitle.animate().scaleX(1).scaleY(1).setDuration(200).start();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                fabLocation.animate().scaleX(1 - slideOffset).scaleY(1 - slideOffset).setDuration(0).start();
            }
        });
    }

    @Override
    public void updateLocation(String locationName) {
        presenter.location(locationName);
        presenter.loadCurrentWeatherData();
        presenter.loadForecastWeatherData();
    }

    @Override
    public void updateCurrentWeatherData(CurrentWeather weather) {
        String location = String.format("%s,%s", weather.getName(), weather.getSys().getCountry());
        sharedPreferences.edit().putString(KEY_LOCATION, location).apply();
        txtCityCountry.setText(location);
        DateFormat df = DateFormat.getDateTimeInstance();
        String updatedAt = df.format(new Date(weather.getDt() * 1000));
        txtImageFont.setTypeface(typeface);
        txtImageFont.setText(WeatherIcons.setWeatherIcon(getContext(), weather.getWeather().get(0).getId(), 8));
        txtLastUpdated.setText(String.format("%s %s", getString(R.string.updated_at), updatedAt));
        txtTemperature.setText(String.valueOf(weather.getMain().getTemp().intValue()));
        txtTempUnit.setText(getString(R.string.unit));
        txtDescription.setText(weather.getWeather().get(0).getDescription());
        txtImgWindSpeed.setTypeface(typeface);
        txtImgWindSpeed.setText(getString(R.string.speed_icon));
        txtImgHumidity.setTypeface(typeface);
        txtImgHumidity.setText(getString(R.string.humidity_icon));
        txtImgPressure.setTypeface(typeface);
        txtImgPressure.setText(getString(R.string.pressure_icon));
        txtWindSpeed.setText(String.format("%s m/s", String.valueOf(weather.getWind().getSpeed())));
        txtHumidity.setText(String.format("%s %s", String.valueOf(weather.getMain().getHumidity()), "%"));
        txtPressure.setText(String.format("%s hpa", String.valueOf(weather.getMain().getPressure())));
    }

    @Override
    public void updateForecastWeatherData(List<WeatherForecast> forecastWeather) {
        adapter.setForecastData(forecastWeather);
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(layoutMain, msg, Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fab_location)
    public void onViewClicked() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        builder.setView(view);

        final EditText editText = view.findViewById(R.id.edt_location);
        Button positiveButton = view.findViewById(R.id.btn_select);
        final AlertDialog dialog = builder.create();
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = editText.getText().toString().trim();
                if (!location.equals("")) {
                    if (!network.isConnected()){
                        showMessage(getString(R.string.no_internet));
                    }
                    else {
                        updateLocation(location);
                    }
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unSubscribe();
        unbinder.unbind();
    }
}
