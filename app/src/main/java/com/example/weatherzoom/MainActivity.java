package com.example.weatherzoom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherzoom.client.ApiClient;
import com.example.weatherzoom.weatherService.WeatherResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView location_val, temperature_val, humidity_val, windspeed_val, pressure_val;
    EditText searchBox;
    Button searchBtn;
    String location_str, temperature_str, humidity_str, windspeed_str, pressure_str;

    private String appid = "ca77e6bf6ebc75babd4e629290eba977";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        searchBtn.setOnClickListener(view -> {
            String loc = searchBox.getText().toString();
            if (loc == null) {
                Toast.makeText(MainActivity.this, "Please enter a location!!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                getData(loc);
            }
        });
    }

    private void setData() {
        location_val.setText(location_str);
        temperature_val.setText(temperature_str);
        humidity_val.setText(humidity_str);
        windspeed_val.setText(windspeed_str);
        pressure_val.setText(pressure_str);
    }

    private void getData(String loc) {
        Call<WeatherResponse> call = ApiClient.getWeatherDetail().getResponse(loc, appid);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    location_str = loc;
                    temperature_str = response.body().getMain().getTemp() + " K";
                    humidity_str = response.body().getMain().getHumidity() + " %";
                    windspeed_str = response.body().getWind().getSpeed() + " mps";
                    pressure_str = response.body().getMain().getPressure() + " hpa";

                    Log.i("bug", "onResponse: temp: " + temperature_str);
                    setData();
                } else {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.errorBody().string());

                        String internalMessage = jsonObject.getString("message");
                        Toast.makeText(MainActivity.this, internalMessage, Toast.LENGTH_SHORT).show();
                        Log.i("responseIsFail", "onResponse: " + internalMessage);

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                Log.i("onFailure", t.getLocalizedMessage());
            }
        });
    }

    private void init() {
        location_val = findViewById(R.id.location_val);
        temperature_val = findViewById(R.id.temp_val);
        humidity_val = findViewById(R.id.humidity_val);
        windspeed_val = findViewById(R.id.windSpeed_val);
        pressure_val = findViewById(R.id.pressure_val);

        searchBox = findViewById(R.id.searchBox);

        searchBtn = findViewById(R.id.searchBtn);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}