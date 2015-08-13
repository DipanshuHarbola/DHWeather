package com.dh.dhweather;

import com.dh.dhweather.beans.Weather;
import com.dh.dhweather.beans.WeatherForecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Deepanshu on 8/12/2015.
 */
public class WeatherDataSet {
    public static Weather getWeatherData(JSONObject json) throws JSONException {
        Weather weather = new Weather();
        weather.setCity(json.getString("name"));
        weather.setCountry(json.getJSONObject("sys").getString("country"));

        JSONObject details = json.getJSONArray("weather").getJSONObject(0);
        JSONObject main = json.getJSONObject("main");

        weather.setDescription(details.getString("description"));
        weather.setIcon(details.getString("icon"));

        weather.setHumidity(main.getString("humidity"));
        weather.setPressure(main.getString("pressure"));
        weather.setTemprature(main.getDouble("temp"));

        weather.setDateTime(json.getLong("dt"));
        return weather;
    }

    // Weather Forecast data
    public static ArrayList<WeatherForecast> getWeatherForecastData(JSONObject json) throws JSONException {
        ArrayList<WeatherForecast> forecastList = new ArrayList<>();
        JSONArray jsonArray = json.getJSONArray("list");
        for (int i=1; i<jsonArray.length();i++){
            JSONObject jObj = jsonArray.getJSONObject(i);
            WeatherForecast weatherForecast = new WeatherForecast();

            weatherForecast.setDay(jObj.getLong("dt"));

            JSONObject jTempObj = jObj.getJSONObject("temp");
            weatherForecast.setMinTemp(jTempObj.getDouble("min"));
            weatherForecast.setMaxTemp(jTempObj.getDouble("max"));
            JSONObject jWeaObj = jObj.getJSONArray("weather").getJSONObject(0);
            weatherForecast.setIcon(jWeaObj.getString("icon"));
            weatherForecast.setDescription(jWeaObj.getString("description"));
            forecastList.add(weatherForecast);
        }

        return forecastList;
    }
}
