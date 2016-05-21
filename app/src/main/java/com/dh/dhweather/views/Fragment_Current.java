package com.dh.dhweather.views;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dh.dhweather.R;
import com.dh.dhweather.SelectCity;
import com.dh.dhweather.WeatherDataGet;
import com.dh.dhweather.WeatherDataSet;
import com.dh.dhweather.beans.Weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Fragment_Current extends Fragment {
    private TextView cityField;
    private TextView updateField;
    private ImageView iconField;
    private TextView tempField;
    private TextView descField;
    private TextView detailsField;
    private TextView deg;
    private TextView notFound;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_current, container, false);
        cityField = (TextView) rootView.findViewById(R.id.cityView);
        updateField = (TextView) rootView.findViewById(R.id.updateView);
        iconField = (ImageView) rootView.findViewById(R.id.iconView);
        tempField = (TextView) rootView.findViewById(R.id.tempView);
        descField = (TextView) rootView.findViewById(R.id.descriptionView);
        detailsField = (TextView) rootView.findViewById(R.id.detailsView);
        deg = (TextView) rootView.findViewById(R.id.degree);
        notFound = (TextView)rootView.findViewById(R.id.notFound);
        progressDialog = new ProgressDialog(getActivity());
        updateCity(new SelectCity(getActivity()).getCity());
        return rootView;
    }

    public void updateCity(String city) {
        new WeatherAsyncTask().execute(city);
    }

    /*private Drawable loadImageFromWeb(String url) {
        Drawable drawable;
        try
        {
            InputStream is = (InputStream) new URL(url).getContent();
            drawable = Drawable.createFromStream(is, "openweathermap");
            return drawable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
*/
    public void changeCity(String city){
        updateCity(city);
    }

    public class WeatherAsyncTask extends AsyncTask<String,Void,Weather>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather;
            final JSONObject json = WeatherDataGet.getWeatherJSON(params[0]);
            try {
                if (json!=null){
                    weather = WeatherDataSet.getWeatherData(json);

                    //weather.image=loadImageFromWeb("http://openweathermap.org/img/w/" + weather.getIcon() + ".png");
                    return weather;
                }
            } catch (JSONException e) {
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if (weather == null){
                notFound.setVisibility(View.VISIBLE);
                notFound.setText(getActivity().getText(R.string.place_not_found));
            }
            else{
                notFound.setVisibility(View.GONE);
                try {
                    progressDialog.dismiss();
                    cityField.setText(weather.getCity() + ", " + weather.getCountry());


                    Glide.with(getActivity())
                            .load("http://openweathermap.org/img/w/" + weather.getIcon() + ".png").into(iconField);
                    //iconField.setImageDrawable(weather.image);

                    descField.setText(weather.getDescription().toUpperCase(Locale.UK));
                    detailsField.setText("Humidity: " + weather.getHumidity() + "%" + "\n" +
                            "Pressure: " + weather.getPressure() + " hpa");

                    tempField.setText(String.format("%.2f", weather.getTemprature()));
                    deg.setText(R.string.unit);

                    DateFormat df = DateFormat.getDateTimeInstance();
                    String updatedOn = df.format(new Date(weather.getDateTime()*1000));
                    updateField.setText("Last Updated: " + updatedOn);

                }catch (Exception e){
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        }
    }
}
