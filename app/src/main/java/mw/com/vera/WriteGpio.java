package mw.com.vera;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Mark on 3/5/17.
 */

public class WriteGpio {
    private File file;



    public void WriteGPIO(String gpioPin, String value) {


        file = new File("/sys/class/gpio/gpio" + gpioPin + "/value");


        Log.d("LOG", gpioPin + " " + value );



        try {
            FileOutputStream fos = new FileOutputStream (file.getAbsolutePath());

            OutputStreamWriter myOutWriter = new OutputStreamWriter(fos);
            myOutWriter.write(value);
            myOutWriter.close();



            fos.close();

        } catch (FileNotFoundException e) {
            Log.i("LOG", "write failed 1 : " +e.getMessage());
        } catch (IOException e) {
            Log.i("LOG", "write failed 2 : " +e.getMessage());
        }
    }
}
