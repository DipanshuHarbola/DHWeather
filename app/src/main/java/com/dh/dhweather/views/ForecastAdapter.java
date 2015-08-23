package com.dh.dhweather.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dh.dhweather.R;
import com.dh.dhweather.beans.WeatherForecast;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class ForecastAdapter extends ArrayAdapter<WeatherForecast> {

    ArrayList<WeatherForecast> objects;
    int resource;
    Context context;
    LayoutInflater layoutInflater;

    public ForecastAdapter(Context context, int resource, ArrayList<WeatherForecast> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource= resource;
        this.objects= objects;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
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
        new DownloadImage(holder.icon).execute(objects.get(position).getIcon());
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("EEEE", Locale.getDefault());
        String todayIs = simpleDateFormat.format(new Date(objects.get(position).getDay() * 1000));
        DateFormat df = DateFormat.getDateInstance();
        String date = df.format(new Date(objects.get(position).getDay() * 1000));
        holder.today.setText(todayIs+" "+date);
        holder.maxTemp.setText(String.format("%.2f", objects.get(position).getMaxTemp())+"\u00B0");
        holder.minTemp.setText(String.format("%.2f",objects.get(position).getMinTemp())+"\u00B0");
        holder.desc.setText(objects.get(position).getDescription());

        return convertView;
    }

    static class ViewHolder{
        public TextView today;
        public TextView maxTemp;
        public TextView minTemp;
        public TextView desc;
        public ImageView icon;
    }

    public  class DownloadImage extends AsyncTask<String,Void,Drawable>{
        ImageView bmImage;

        public DownloadImage(ImageView imageView){
            bmImage = imageView;
        }

        @Override
        protected Drawable doInBackground(String... params) {
            Drawable drawable = null;
            try{
                InputStream in = new java.net.URL("http://openweathermap.org/img/w/"+params[0]+".png").openStream();
                drawable = Drawable.createFromStream(in , "openweathermap");
            }catch (Exception e){
                e.printStackTrace();
            }
            return drawable;
        }

        @Override
        protected void onPostExecute(Drawable drawable) {

            bmImage.setImageDrawable(drawable);
        }
    }
}
