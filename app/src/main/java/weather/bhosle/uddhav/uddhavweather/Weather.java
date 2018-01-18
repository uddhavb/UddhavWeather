package weather.bhosle.uddhav.uddhavweather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Weather extends AppCompatActivity {
    final int numberOfDays = 10;
    ListView list;
    //temporarily
    String[] weekDays = new String[numberOfDays];

    String[] description = new String[numberOfDays];
   // Integer[] imgid = new Integer[numberOfDays];
    //Bitmap[] bmp = new Bitmap[numberOfDays];
    String[] imgurl = new String[numberOfDays];
    /*
    Integer[] imgid={ R.drawable.pic0,
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic0,
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4

            };
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        //get the data from the api
        String data = getIntent().getExtras().getString("dataToWeather");
        try {
           // System.out.println("NCSU ------------------------NCSU---------------------------NCSU------------------------NCSU");
            JSONObject json = new JSONObject(data);
            json = json.getJSONObject("forecast");
            json = json.getJSONObject("txt_forecast");
            JSONArray jsonArray = json.getJSONArray("forecastday");
            for(int i=0;i < jsonArray.length(); i = i + 2)
            {
                weekDays[i/2] = (jsonArray.getJSONObject(i)).getString("title");
                description[i/2] = (jsonArray.getJSONObject(i)).getString("fcttext");
                // imgid[i/2] = (jsonArray.getJSONObject(i)).getString("fcttext");
                imgurl[i/2] = (jsonArray.getJSONObject(i)).getString("icon_url");
            }
            CustomListAdapter adapter=new CustomListAdapter(this, weekDays, imgurl, description);
            list = (ListView) findViewById(R.id.list10days);
            list.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
