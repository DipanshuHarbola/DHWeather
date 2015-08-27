package com.dh.dhweather;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.dh.dhweather.views.Fragment_Current;
import com.dh.dhweather.views.Fragment_Forecast;

import java.util.Locale;


@SuppressWarnings("deprecation")
public class WeatherActivity extends AppCompatActivity implements ActionBar.TabListener {


    SectionsPagerAdapter mSectionsPagerAdapter;


    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_weather);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setStatusBarColor(getResources().getColor(R.color.statusbar));
        }
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);

                if (mViewPager.getCurrentItem() == 1) {
                    Fragment_Forecast forecastFragment = (Fragment_Forecast) mViewPager.getAdapter().instantiateItem(mViewPager, mViewPager.getCurrentItem());
                    forecastFragment.changeCity();
                }
            }
        });


        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {

            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.newCity:
                showCityDialog();
                break;
            case R.id.refresh:
                refreshApp();
                break;
        }

        return true;
    }


    private void showCityDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change City");
        final EditText editText = new EditText(this);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(editText);

        builder.setPositiveButton("Set City", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg) {
                changeCity(editText.getText().toString());
            }
        });
        builder.show();
    }

    public void changeCity(String city) {
        if (mViewPager.getCurrentItem() == 0) {
            Fragment_Current weatherFragment = (Fragment_Current) mViewPager.getAdapter().instantiateItem(mViewPager, mViewPager.getCurrentItem());
            weatherFragment.changeCity(city);
            new SelectCity(this).setCity(city);

        }
    }

    private void refreshApp() {
        if (mViewPager.getCurrentItem() == 0) {
            Fragment_Current currentFragment = (Fragment_Current) mViewPager.getAdapter().instantiateItem(mViewPager, mViewPager.getCurrentItem());
            currentFragment.changeCity(new SelectCity(this).getCity());
            Toast.makeText(WeatherActivity.this, "...Refresh successfully...", Toast.LENGTH_SHORT).show();
        }
        if (mViewPager.getCurrentItem() == 1) {
            Fragment_Forecast forecastFragment = (Fragment_Forecast) mViewPager.getAdapter().instantiateItem(mViewPager, mViewPager.getCurrentItem());
            forecastFragment.changeCity();
            Toast.makeText(WeatherActivity.this, "...Refresh successfully...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Fragment_Current();
                case 1:
                    return new Fragment_Forecast();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);

            }
            return null;
        }
    }



}