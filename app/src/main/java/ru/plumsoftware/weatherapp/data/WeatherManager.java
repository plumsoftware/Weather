package ru.plumsoftware.weatherapp.data;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.plumsoftware.weatherapp.api.ApiDailyInterface;
import ru.plumsoftware.weatherapp.api.ApiInterface;
import ru.plumsoftware.weatherapp.data.Settings;
import ru.plumsoftware.weatherapp.weatherdata.current.CurrentWeather;
import ru.plumsoftware.weatherapp.weatherdata.forecast.ForecastWeather;

public class WeatherManager {
    private String lang;
    private String city;
    private String api_key;

    public WeatherManager(Context context) {
        super();
        lang = Settings.getUserSettings(context).getLang();
        city = Settings.getUserSettings(context).getQ();
    }

    public Call<CurrentWeather> getCurrentWeather(String q, String appid, String units, String lang) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        return apiInterface.getCurrentWeather(q, appid, units, lang);
    }

    public Call<ForecastWeather> getDailyWeather(String key, String q, int days, String aqi, String alerts, String lang) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiDailyInterface apiDailyInterface = retrofit.create(ApiDailyInterface.class);

        return apiDailyInterface.getForecastWeather(key, q, days, aqi, alerts, lang);
    }
}
