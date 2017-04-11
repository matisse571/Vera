package mw.com.vera;

/**
 * Created by Mark on 3/9/17.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class RemoteFetch {


    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&units=imperial";


    public static JSONObject getJSON(String city, String apiKey){
        try {
            Log.d("LOG", "getJSON(");
            URL url = new URL(String.format(OPEN_WEATHER_MAP_API, city));
            Log.d("url", url.toString());
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();
            Log.d("urla",connection.toString());
            connection.addRequestProperty("x-api-key",apiKey);
                   // context.getString(R.string.open_weather_maps_app_id));
            Log.d("urla",connection.toString());
            Log.d("urlb",url.toString());

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            Log.d("json.toString", (json.toString()));

            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                Log.d("LOG", "return null");
                return null;
            }
            Log.d("LOG", "return data");
            return data;

        }catch(Exception e){
            return null;
        }
    }
}