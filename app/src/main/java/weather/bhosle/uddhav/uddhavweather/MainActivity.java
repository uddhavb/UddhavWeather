package weather.bhosle.uddhav.uddhavweather;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static String data = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button weather = findViewById(R.id.weather);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get the name of the city
                EditText editText = (EditText) findViewById(R.id.cityname);
                String city;
                city = editText.getText().toString();
                FetchData getFromAPI = new FetchData(city,getBaseContext());
                getFromAPI.execute();
            }
        });

    }
}
