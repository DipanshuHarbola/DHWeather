package com.dh.dhweather.beans;

import android.graphics.drawable.Drawable;

public class Weather
{
    private String city;
    private String country;
    private long dateTime;
    private String description;
    private String humidity;
    private String icon;
    public Drawable image;
    private String pressure;
    private double temprature;

    public String getCity()
    {
        return this.city;
    }

    public String getCountry()
    {
        return this.country;
    }

    public long getDateTime()
    {
        return this.dateTime;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String getHumidity()
    {
        return this.humidity;
    }

    public String getIcon()
    {
        return this.icon;
    }



    public String getPressure()
    {
        return this.pressure;
    }

    public double getTemprature()
    {
        return this.temprature;
    }

    public void setCity(String paramString)
    {
        this.city = paramString;
    }

    public void setCountry(String paramString)
    {
        this.country = paramString;
    }

    public void setDateTime(long paramLong)
    {
        this.dateTime = paramLong;
    }

    public void setDescription(String paramString)
    {
        this.description = paramString;
    }

    public void setHumidity(String paramString)
    {
        this.humidity = paramString;
    }

    public void setIcon(String paramString)
    {
        this.icon = paramString;
    }

    public void setPressure(String paramString)
    {
        this.pressure = paramString;
    }

    public void setTemprature(double paramDouble)
    {
        this.temprature = paramDouble;
    }
}
