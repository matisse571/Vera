package mw.com.vera;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Mark on 3/6/17.
 */

public class ReadSensor {
    private File file;
    public String readSensor() {

        String ret = "";
        file = new File("/sys/bus/i2c/devices/2-005a/input/input3/ps_input_data");

        try {
            //FileInputStream fis = openFileInput("/sys/bus/i2c/devices/2-005a/input/input3/ps_input_data");
            FileInputStream fis = new FileInputStream (file.getAbsolutePath());
            if ( fis != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                fis.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("TAG in", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("TAG in", "Can not read file: " + e.toString());
        }
       // Log.e("TAG in", ret);
        return ret;

    }
}
