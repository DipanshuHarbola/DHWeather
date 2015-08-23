package com.dh.dhweather;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WeatherDataGet {
    private static final String CURRENT_WEATHER_CONDITION = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
    private static final String WEATHER_FORECAST="http://api.openweathermap.org/data/2.5/forecast/daily?q=%s&mode=json&units=metric&cnt=7";


    public static JSONObject getWeatherJSON(String city){

        try{
            URL url = new URL(String.format(CURRENT_WEATHER_CONDITION,city));

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.addRequestProperty("x-api-key","bb6339ef414ea92c1b02014ccdacf41f");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp ;

            while((tmp=reader.readLine())!=null){
                json.append(tmp).append("\n");
            }

            reader.close();

            JSONObject data = new JSONObject(json.toString());

            if(data.getInt("cod") != 200)
                return null;

            return data;
        }catch (Exception e){
            return null;
        }
    }



    public static JSONObject getForecastJson(String city){
        try{
            URL url = new URL(String.format(WEATHER_FORECAST,city));

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.addRequestProperty("x-api-key","bb6339ef414ea92c1b02014ccdacf41f");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp ;

            while((tmp=reader.readLine())!=null){
                json.append(tmp).append("\n");
            }

            reader.close();

            JSONObject data = new JSONObject(json.toString());

            if(data.getInt("cod") != 200)
                return null;

            return data;
        }catch (Exception e){
            return null;
        }

    }
}

