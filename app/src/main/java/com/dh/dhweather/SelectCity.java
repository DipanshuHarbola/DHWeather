package com.dh.dhweather;

/**
 * Created by Deepanshu on 8/12/2015.
 */
import android.app.Activity;
import android.content.SharedPreferences;

public class SelectCity
{
    private static final String SHARED_PREF_CITY_KEY_NAME = "CITY";

    SharedPreferences preferences;

    public SelectCity(Activity paramActivity)
    {
        this.preferences = paramActivity.getPreferences(0);
    }

    public String getCity()
    {
        return this.preferences.getString(SHARED_PREF_CITY_KEY_NAME, "Delhi,IN");
    }
    public void setCity(String paramString)
    {
        this.preferences.edit().putString(SHARED_PREF_CITY_KEY_NAME, paramString).apply();
    }


}

