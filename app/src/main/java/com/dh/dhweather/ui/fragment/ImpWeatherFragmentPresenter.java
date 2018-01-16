package com.dh.dhweather.ui.fragment;


import android.util.Log;

import com.dh.dhweather.services.pojo.CurrentWeather;
import com.dh.dhweather.services.pojo.ForecastWeather;
import com.dh.dhweather.services.repository.current.CurrentWeatherData;
import com.dh.dhweather.services.repository.forecast.ForecastWeatherData;
import com.dh.dhweather.utils.WeatherDataSet;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

import static com.dh.dhweather.utils.Constants.KEY_APP_ID;
import static com.dh.dhweather.utils.Constants.KEY_RESULT_COUNT;
import static com.dh.dhweather.utils.Constants.KEY_RESULT_MODE;
import static com.dh.dhweather.utils.Constants.KEY_TEMP_UNIT;

public class ImpWeatherFragmentPresenter implements WeatherFragmentContract.Presenter {

    private static final String TAG = ImpWeatherFragmentPresenter.class.getName();
    private CurrentWeatherData mCurrentWeatherData;
    private ForecastWeatherData mForecastWeatherData;
    private WeatherFragmentContract.View mWeatherFragmentView;
    private CompositeDisposable mCompositeDisposable;

    private String locationName;

    @Inject
    public ImpWeatherFragmentPresenter(CurrentWeatherData mCurrentWeatherData,
                                       ForecastWeatherData mForecastWeatherData,
                                       WeatherFragmentContract.View mWeatherFragmentView) {
        this.mCurrentWeatherData = mCurrentWeatherData;
        this.mForecastWeatherData = mForecastWeatherData;
        this.mWeatherFragmentView = mWeatherFragmentView;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void location(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public void loadCurrentWeatherData() {
        Disposable disposable = mCurrentWeatherData.getCurrentWeather(locationName,KEY_TEMP_UNIT,KEY_APP_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<CurrentWeather>() {
                    @Override
                    public void onNext(CurrentWeather currentWeather) {
                        Log.i(TAG, "Server response - "+currentWeather);
                        mWeatherFragmentView.updateCurrentWeatherData(currentWeather);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, t.getMessage());
                        try {
                            String erroeJson = ((retrofit2.adapter.rxjava2.HttpException)t).response().errorBody().source().readUtf8();
                            JSONObject jsonObject = new JSONObject(erroeJson);
                            String errorMessage = jsonObject.getString("message");
                            mWeatherFragmentView.showMessage(errorMessage);
                        } catch (NullPointerException | IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadForecastWeatherData() {
        Disposable disposable = mForecastWeatherData.getForecastWeather(locationName,KEY_TEMP_UNIT,KEY_RESULT_MODE,KEY_RESULT_COUNT,KEY_APP_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ForecastWeather>() {
                    @Override
                    public void onNext(ForecastWeather forecastWeather) {
                        Log.i(TAG, "Server response - "+forecastWeather);
                        mWeatherFragmentView.updateForecastWeatherData(WeatherDataSet.getWeatherForecastData(forecastWeather));
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void unSubscribe() {
        mWeatherFragmentView = null;
        mCompositeDisposable.clear();
    }
}
