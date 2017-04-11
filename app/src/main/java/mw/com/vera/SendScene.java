package mw.com.vera;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mark on 3/5/17.
 */

public class SendScene {

    public void sendScene(String sceneNumber) {
        Log.d("TAG observer","sendscene");
        try {
            String urlString = "http://192.168.4.108/port_3480/data_request?id=lu_action&serviceId=urn:micasaverde-com:serviceId:HomeAutomationGateway1&action=RunScene&SceneNum=" + sceneNumber;
            URL url = new URL(urlString);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            //BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            url.openStream();

            String line;



            while ((line = reader.readLine()) != null) {
                Log.i("log", line);
            }


            reader.close();

        } catch (MalformedURLException e) {
            // System.out.println("The URL is not valid.");
            Log.i("LOG", "The URL is not valid.: " + e.getMessage());
            // System.out.println(e.getMessage());
        } catch (IOException e) {
            Log.i("LOG", "IOException : " + e.getMessage());
            // e.printStackTrace();
        }


    }
}