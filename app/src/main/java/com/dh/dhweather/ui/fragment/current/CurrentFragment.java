package com.dh.dhweather.ui.fragment.current;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dh.dhweather.R;
import com.dh.dhweather.beans.Weather;
import com.dh.dhweather.ui.base.BaseFragment;

import javax.inject.Inject;

public class CurrentFragment extends BaseFragment implements CurrentFragmentContract.View {

    @Inject
    CurrentFragmentContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.subscribe();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_current1, container, false);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unSubscribe();
    }

    @Override
    public void updateData(Weather weather) {

    }

    @Override
    public void showMessage(String msg) {

    }
}
