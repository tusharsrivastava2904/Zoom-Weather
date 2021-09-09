package com.example.weatherzoom.client;

import com.example.weatherzoom.weatherService.WeatherResponse;
import com.example.weatherzoom.weatherService.weatherService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.
                Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.openweathermap.org/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static weatherService getWeatherDetail() {
        weatherService weatherService = getRetrofit().create(com.example.weatherzoom.weatherService.weatherService.class);
        return weatherService;
    }
}
