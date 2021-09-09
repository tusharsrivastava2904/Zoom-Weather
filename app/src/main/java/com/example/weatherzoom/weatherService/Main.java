package com.example.weatherzoom.weatherService;

public class Main {
    double feels_like;
    int humidity;
    int pressure;
    double temp;
    double temp_max;
    double getTemp_min;

    public double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getGetTemp_min() {
        return getTemp_min;
    }

    public void setGetTemp_min(double getTemp_min) {
        this.getTemp_min = getTemp_min;
    }
}
