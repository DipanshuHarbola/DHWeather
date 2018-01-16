package com.dh.dhweather.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.dh.dhweather.R;
import com.dh.dhweather.ui.base.BaseActivity;
import com.dh.dhweather.ui.fragment.WeatherFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_container)
    FrameLayout mainContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_container, new WeatherFragment());
            transaction.commit();
        }
    }

}