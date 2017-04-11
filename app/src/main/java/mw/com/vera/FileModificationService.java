package mw.com.vera;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 3/6/17.
 */

public class FileModificationService extends Service {


    //private MyFileObserver fileOb;
    //private static final int MAX_FO = 1;
    private List<MyFileObserver> fileOb_list = new ArrayList<MyFileObserver>();

    @Override

    public void onCreate() {
        Log.d("TAG observer","createFileObs");
        //if (!EnvironmentUtilsStatic.is_external_storage_available()) {
        //Toast.makeText(FileModificationService.this, "SDCARD is not available!", Toast.LENGTH_SHORT).show();
        //	return;
        //}

        File fff = new File("/dev/input/event0");
      //File fff = new File("/sys/bus/i2c/devices/2-005a/input/input3/ps_input_data");
        // File fff = new File("/sys/devices/virtual/gpio/gpio7/value");
        //File fff = new File("/sys/class/gpio/gpio7/");
        // File fff = new File("/sys/devices/virtual/gpio/gpio7/");
        //File fff = new File("/dev/input/event0");



        createFileObs(fff);
        //  fileOb.startWatching();


        //  createFileObs(ff);
        //  fileOb.startWatching();
        Log.d("TAG observer","createFileObs");

    }

    //only create fileobserver for folders
    int num_of_fos = 0;
    private void createFileObs(File f) {
		/*if (num_of_fos > MAX_FO) {
			return;
		}*/
        //if (!f.isDirectory()) {
        //MyFileObserver aFileOb = new MyFileObserver(f.getAbsolutePath());
        //fileOb_list.add(aFileOb);
        //} else {
        //fileOb = new MyFileObserver(f.getAbsolutePath());
        Log.d("TAG observer","ret3");
        Log.d("TAG observer2",f.getAbsolutePath());
        MyFileObserver aFileOb = new MyFileObserver(f.getAbsolutePath());
        fileOb_list.add(aFileOb);
        Log.d("TAG observer","1");
        num_of_fos++;
        Log.d("TAG observer","2");

		/*	try {
				for (File currentFile : f.listFiles()) {
                    Log.d("TAG observer","3a");
                    createFileObs(currentFile);
                    Log.d("TAG observer","3");
				}
			} catch (Exception e) {
				Log.e("Error", e.toString());
			}*/
        //}
    }

    @Override
    public void onStart(Intent intent, int startid) {
        //fileOb.startWatching();
        Log.d("TAG observer","fileOb.startWatching");
        Log.d("TAG observer",String.valueOf(fileOb_list.size()));

        for (int i = 0; i < fileOb_list.size(); ++i) {
            fileOb_list.get(i).startWatching();
            Log.d("TAG observer",String.valueOf(fileOb_list.get(i)));
        }
        Toast.makeText(this.getApplicationContext(), "start monitoring file modification", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroy() {
        //fileOb.stopWatching();
        for (int i = 0; i < fileOb_list.size(); ++i) {
            fileOb_list.get(i).stopWatching();
            Log.d("TAG observer","fileOb.stopWatching");
            Log.d("TAG observer",String.valueOf(fileOb_list.size()));
        }
        Toast.makeText(this.getApplicationContext(), "stop monitoring file modification", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }
}
