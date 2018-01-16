package com.dh.dhweather.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dh.dhweather.R;
import com.dh.dhweather.ui.fragment.WeatherForecast;
import com.dh.dhweather.utils.WeatherIcons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.MyViewHolder> {

    private Context mContext;
    private Typeface typeface;
    private List<WeatherForecast> forecastData = new ArrayList<>();

    public ForecastAdapter(Context context, Typeface typeface) {
        this.mContext = context;
        this.typeface = typeface;
    }

    public void setForecastData(List<WeatherForecast> data) {
        if (data == null) {
            return;
        }
        forecastData.clear();
        forecastData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.forecast_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindViews(mContext,forecastData.get(position),typeface);
    }

    @Override
    public int getItemCount() {
        return forecastData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_date)
        TextView txtDate;
        @BindView(R.id.txt_image_font)
        TextView txtImageFont;
        @BindView(R.id.txt_tmp_max)
        TextView txtTmpMax;
        @BindView(R.id.txt_tmp_min)
        TextView txtTmpMin;
        @BindView(R.id.txt_description)
        TextView txtDescription;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        public void bindViews(Context mContext, WeatherForecast forecast, Typeface typeface){
            DateFormat format = new SimpleDateFormat("EEEE dd MMM yyyy", Locale.getDefault());
            String date = format.format(new Date(forecast.getDay()*1000));
            txtDate.setText(date);
            txtImageFont.setTypeface(typeface);
            txtImageFont.setText(WeatherIcons.setWeatherIcon(mContext,forecast.getIconId(),2));
            txtTmpMax.setText(String.format("%s %s",String.valueOf(forecast.getMaxTemp()),mContext.getString(R.string.unit)));
            txtTmpMin.setText(String.format("%s %s",String.valueOf(forecast.getMinTemp()),mContext.getString(R.string.unit)));
            txtDescription.setText(forecast.getDescription());
        }
    }

}
