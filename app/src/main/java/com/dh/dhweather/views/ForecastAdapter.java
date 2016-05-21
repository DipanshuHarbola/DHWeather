package com.dh.dhweather.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dh.dhweather.R;
import com.dh.dhweather.beans.WeatherForecast;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.MyViewHolder> {

    ArrayList<WeatherForecast> objects;
    int resource;
    Context context;

    public ForecastAdapter(Context context, int resource, ArrayList<WeatherForecast> objects) {
        this.context=context;
        this.resource= resource;
        this.objects= objects;
        Log.e("inside","ForecastAdapter");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        Log.e("inside","MyViewHolder");
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.e("inside","onBindViewHolder");
        Glide.with(context)
                .load("http://openweathermap.org/img/w/" + objects.get(position).getIcon() + ".png").into(holder.icon);
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("EEEE", Locale.getDefault());
        String todayIs = simpleDateFormat.format(new Date(objects.get(position).getDay() * 1000));
        DateFormat df = DateFormat.getDateInstance();
        String date = df.format(new Date(objects.get(position).getDay() * 1000));
        holder.today.setText(todayIs+" "+date);
        holder.maxTemp.setText(String.format("%.2f", objects.get(position).getMaxTemp())+"\u00B0");
        holder.minTemp.setText(String.format("%.2f",objects.get(position).getMinTemp())+"\u00B0");
        holder.desc.setText(objects.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(resource,null);
            holder = new ViewHolder();
            holder.today = (TextView) convertView.findViewById(R.id.today);
            holder.maxTemp = (TextView) convertView.findViewById(R.id.maxTemp);
            holder.minTemp = (TextView) convertView.findViewById(R.id.minTemp);
            holder.desc = (TextView) convertView.findViewById(R.id.desc);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);



            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        Glide.with(context)
                .load("http://openweathermap.org/img/w/" + objects.get(position).getIcon() + ".png").into(holder.icon);
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("EEEE", Locale.getDefault());
        String todayIs = simpleDateFormat.format(new Date(objects.get(position).getDay() * 1000));
        DateFormat df = DateFormat.getDateInstance();
        String date = df.format(new Date(objects.get(position).getDay() * 1000));
        holder.today.setText(todayIs+" "+date);
        holder.maxTemp.setText(String.format("%.2f", objects.get(position).getMaxTemp())+"\u00B0");
        holder.minTemp.setText(String.format("%.2f",objects.get(position).getMinTemp())+"\u00B0");
        holder.desc.setText(objects.get(position).getDescription());

        return convertView;
    }*/


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView today;
        public TextView maxTemp;
        public TextView minTemp;
        public TextView desc;
        public ImageView icon;

        public MyViewHolder(View view){
            super(view);
            today = (TextView) view.findViewById(R.id.today);
            maxTemp = (TextView) view.findViewById(R.id.maxTemp);
            minTemp = (TextView) view.findViewById(R.id.minTemp);
            desc = (TextView) view.findViewById(R.id.desc);
            icon = (ImageView) view.findViewById(R.id.icon);
        }
    }

}
