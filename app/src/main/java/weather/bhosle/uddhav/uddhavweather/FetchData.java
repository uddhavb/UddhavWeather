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

    public final String[] states = new String[]{"Armed Forces America",
            "Armed Forces", "Armed Forces Pacific", "Alaska", "Alabama", "Arkansas", "Arizona", "California", "Colorado", "Connecticut",
            "Washington DC", "Delaware", "Florida", "Georgia", "Guam", "Hawaii", "Iowa", "Idaho", "Illinois", "Indiana",
            "Kansas", "Kentucky", "Louisiana", "Massachusetts", "Maryland", "Maine", "Michigan", "Minnesota", "Missouri", "Mississippi",
            "Montana", "North Carolina", "North Dakota", "Nebraska", "New Hampshire", "New_Jersey", "New Mexico", "Nevada",
            "New York", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota", "Tennessee",
            "Texas", "Utah", "Virginia", "Virgin Islands", "Vermont", "Washington", "Wisconsin", "West Virginia", "Wyoming"};
    public final String[] stateCodes = {"AA","AE", "AP", "AK","AL", "AR", "AZ", "CA", "CO",
            "CT", "DC", "DE", "FL","GA","GU","HI", "IA", "ID", "IL", "IN", "KS", "KY" ,"LA", "MA", "MD",
            "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV","NY","OH", "OK", "OR",
                        "PA","PR","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY"};

    public static Context context;

    String url = "";
    String City="";
    String State="";
    FetchData(String city, Context context)
    {
        City = city;
        this.context = context;
    }


    String jsonData = "";

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            //get the city and state.
            URL cityState = new URL("http://autocomplete.wunderground.com/aq?query=" + City + "&c=US");
            HttpURLConnection connectToAutoFill = (HttpURLConnection) cityState.openConnection();
            InputStream input = connectToAutoFill.getInputStream();
            BufferedReader br =new BufferedReader(new InputStreamReader(input));
            String line = "";
            while(line != null)
            {
                line = br.readLine();
                if(line==null || line.contains(","))
                    break;
            }
            if(line==null)
            {
                return null;
            }
            line = line.substring(8);
            int endIndex = line.indexOf(',');
            City =line.substring(line.indexOf(":") + 3,endIndex);
            line = line.substring(endIndex+1);
            State = line.substring(1,line.indexOf(",")-1);

            //remove spaces and replace with underscore
            City = City.replaceAll(" ", "_");
            System.out.println("CITY TO API URL _________________________" + City);
            for(int i=0;i<states.length;i++)
            {
                if(states[i].equals(State))
                {
                    State = stateCodes[i];
                    break;
                }
            }
            System.out.println("STATE TO API URL _________________________" + State);
            URL wunderGround = new URL("http://api.wunderground.com/api/24f0b7e4ed53f605/forecast10day/q/"+State+"/"+City+".json");
            HttpURLConnection urlconnection = (HttpURLConnection) wunderGround.openConnection();
            InputStream inputStream = urlconnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            line = "";
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
