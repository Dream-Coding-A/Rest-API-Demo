package com.example.restapidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView currentDateTime, dayoftheWeek, currentFileTime;
    Button utc, est;
    API_Interface api_interface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDateTime = findViewById(R.id.currentTimeID);
        dayoftheWeek = findViewById(R.id.dayoftheWeek);
        currentFileTime = findViewById(R.id.currentfiletime);
        utc = findViewById(R.id.utcID);
        est = findViewById(R.id.estID);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://worldclockapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api_interface = retrofit.create(API_Interface.class);

        utc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UTCdate_time();
            }
        });
        est.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ESTdate_time();
            }
        });
    }

    public void UTCdate_time() {
        api_interface.getUTCtime().enqueue(new Callback<CallingAPI>() {
            @Override
            public void onResponse(Call<CallingAPI> call, Response<CallingAPI> response) {

                currentDateTime.setText(response.body().getCurrentDateTime());
                dayoftheWeek.setText(response.body().getDayOfTheWeek());
                currentFileTime.setText(String.valueOf(response.body().getCurrentFileTime()));

            }

            @Override
            public void onFailure(Call<CallingAPI> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connect to the internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ESTdate_time() {
        api_interface.getESTtime().enqueue(new Callback<CallingAPI>() {
            @Override
            public void onResponse(Call<CallingAPI> call, Response<CallingAPI> response) {
                currentDateTime.setText(response.body().getCurrentDateTime());
                dayoftheWeek.setText(response.body().getDayOfTheWeek());
                currentFileTime.setText(String.valueOf(response.body().getCurrentFileTime()));
            }

            @Override
            public void onFailure(Call<CallingAPI> call, Throwable t) {
                Log.d("test", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}