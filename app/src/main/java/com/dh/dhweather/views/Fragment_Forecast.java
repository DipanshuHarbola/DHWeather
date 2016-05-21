package com.dh.dhweather.views;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private TextView notFound;
    private RecyclerView listView;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);
        notFound = (TextView) rootView.findViewById(R.id.notFound);
        listView = (RecyclerView)rootView.findViewById(R.id.listView);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        listView.setLayoutManager(llm);
        progressDialog = new ProgressDialog(getActivity());
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
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading...");
            progressDialog.setCanceledOnTouchOutside(false);
            //progressDialog.show();
        }

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
                progressDialog.dismiss();
                notFound.setVisibility(View.VISIBLE);
                notFound.setText(getActivity().getText(R.string.place_not_found));
            }
            else {
                progressDialog.dismiss();
                notFound.setVisibility(View.GONE);
                ForecastAdapter adapter = new ForecastAdapter(getActivity(),R.layout.forecast_list,list);
                listView.setAdapter(adapter);


            }
        }
    }
}
