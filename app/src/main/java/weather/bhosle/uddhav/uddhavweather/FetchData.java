package weather.bhosle.uddhav.uddhavweather;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bhosl on 1/18/2018.
 */


public class FetchData extends AsyncTask {

    public static Context context;

    String url = "";
    FetchData(String city, Context context)
    {
        url = "http://api.wunderground.com/api/24f0b7e4ed53f605/forecast10day/q/CA/" + city +".json";
        this.context = context;
    }


    String jsonData = "";

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL wunderGround = new URL("http://api.wunderground.com/api/24f0b7e4ed53f605/forecast10day/q/CA/San_Francisco.json");
            HttpURLConnection urlconnection = (HttpURLConnection) wunderGround.openConnection();
            InputStream inputStream = urlconnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while(line != null)
            {
                line = bufferedReader.readLine();
                jsonData += line;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            ///////
            //if the api call did not work then do something over here.
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        //MainActivity.data = jsonData;
        //MainActivity.tv.setText(jsonData);
        Intent toWeather = new Intent(context, Weather.class);
        toWeather.putExtra("dataToWeather",jsonData);
        context.startActivity(toWeather);
    }
}
