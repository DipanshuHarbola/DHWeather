package com.dh.dhweather.views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.dh.dhweather.R;
import com.dh.dhweather.SelectCity;
import com.dh.dhweather.WeatherDataGet;
import com.dh.dhweather.WeatherDataSet;
import com.dh.dhweather.beans.WeatherForecast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_Forecast extends Fragment {
    ArrayList<WeatherForecast> list;
    private TextView textView;
    private TextView notFound;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);
        textView = (TextView) rootView.findViewById(R.id.forecasting);
        notFound = (TextView) rootView.findViewById(R.id.notFound);
        listView = (ListView)rootView.findViewById(R.id.listView);
        list = new ArrayList<>();
        updateCity(new SelectCity(getActivity()).getCity());
        return rootView;
    }

    public void updateCity(String city) {
        new WeatherForecastTask().execute(city);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (menu != null){
            menu.findItem(R.id.newCity).setVisible(false);
        }
    }

    public void changeCity() {
        updateCity(new SelectCity(getActivity()).getCity());
    }

    public class WeatherForecastTask extends AsyncTask<String,Void,Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            final JSONObject json = WeatherDataGet.getForecastJson(params[0]);
            try {
                if (json!=null) {
                    list = WeatherDataSet.getWeatherForecastData(json);
                    return true;
                }


            } catch (JSONException e) {
                return false;
            }




            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (!aBoolean) {
                notFound.setVisibility(View.VISIBLE);
                notFound.setText(getActivity().getText(R.string.place_not_found));
            }
            else {
                notFound.setVisibility(View.GONE);
                textView.setText(getActivity().getText(R.string.forecasting));
                ForecastAdapter adapter = new ForecastAdapter(getActivity(),R.layout.forecast_list,list);
                listView.setAdapter(adapter);


            }
        }
    }
}
