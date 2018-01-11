package com.dh.dhweather.ui.fragment.current;


import com.dh.dhweather.beans.Weather;
import com.dh.dhweather.ui.base.BasePresenter;

public class CurrentFragmentContract {

    public interface View {

        void updateData(Weather weather);

        void showMessage(String msg);

    }

    public interface Presenter extends BasePresenter {

        void location(String locationName);

        void loadData();

    }

}
