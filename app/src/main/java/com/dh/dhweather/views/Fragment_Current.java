package com.dh.dhweather.views;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * Created by Deepanshu on 8/12/2015.
 */
public class Fragment_Current extends Fragment {
    private TextView cityField;
    private TextView updateField;
    private ImageView iconField;
    private TextView tempField;
    private TextView descField;
    private TextView detailsField;
    private TextView deg;

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
        updateCity(new SelectCity(getActivity()).getCity());
        return rootView;
    }

    public void updateCity(String city) {
        new WeatherAsyncTask().execute(city);
    }

    public class WeatherAsyncTask extends AsyncTask<String,Void,Weather>{

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            final JSONObject json = WeatherDataGet.getWeatherJSON(params[0]);
            try {
                weather = WeatherDataSet.getWeatherData(json);

                // Let's retrieve the icon
                weather.image=loadImageFromWeb("http://openweathermap.org/img/w/"+weather.getIcon()+".png");

            } catch (JSONException e) {
                e.printStackTrace();
            }


            Log.e("doInBackground() is", "Ok");
            if (json==null)
                Toast.makeText(getActivity(), getActivity().getString(R.string.place_not_found), Toast.LENGTH_LONG).show();
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            try {
                cityField.setText(weather.getCity() + ", " + weather.getCountry());


                iconField.setImageDrawable(weather.image);

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
            }
        }
    }

    private Drawable loadImageFromWeb(String url) {
        try
        {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable drawable = Drawable.createFromStream(is, "src name");
            //Toast.makeText(new MainActivity(),"inside loadingimg method\n"+url,Toast.LENGTH_LONG).show();
            return drawable;
        } catch (Exception e) {
            return null;
        }
    }

    public void changeCity(String city){
        updateCity(city);
    }
}
