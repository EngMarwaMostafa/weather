package com.example.weatherr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherr.RETROFIT.ApiClient;
import com.example.weatherr.RETROFIT.ApiInterface;
import com.example.weatherr.RETROFIT.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tempText,descText,humidityText;
    ImageView search;
    EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      search = findViewById(R.id.search);
      tempText = findViewById(R.id.tempText);
      humidityText = findViewById(R.id.humidityText);
      textField = findViewById(R.id.textField);
      descText = findViewById(R.id.descText);
      
      search.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              getWeatherData(textField.getText().toString().trim());
          }

          private void getWeatherData(String name) {
              ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
              Call<Example> call = apiInterface.getWeatherData(name);

              call.enqueue(new Callback<Example>() {
                  @Override
                  public void onResponse(Call<Example> call, Response<Example> response) {

                     tempText.setText("Temp"+" "+response.body().getMain().getTemp()+" C");
                      descText.setText("Feels Like"+" "+response.body().getMain().getFeels_like());
                      humidityText.setText("Humidity"+" "+response.body().getMain().getHumidity());
                  }

                  @Override
                  public void onFailure(Call<Example> call, Throwable t) {

                  }
              });
          }
      });


    }
}