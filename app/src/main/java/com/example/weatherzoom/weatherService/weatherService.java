package com.example.weatherzoom.weatherService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface weatherService {
    @GET("data/2.5/weather")
    Call<WeatherResponse> getResponse(
            @Query("q") String q,
            @Query("appid") String appid
    );
}
