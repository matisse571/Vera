package mw.com.vera;

import android.os.FileObserver;
import android.util.Log;


import java.io.File;


/**
 * Created by Mark on 3/6/17.
 */

public class MyFileObserver extends FileObserver {
    public String absolutePath;






    private File file;




    public MyFileObserver(String path) {
        super(path, FileObserver.ALL_EVENTS);
        absolutePath = path;
        Log.d("TAG observer", String.valueOf(absolutePath));
        //  Log.d("TAG observer",String.valueOf(fileOb_list.size()));
    }

    @Override
    public void onEvent(int event, String path) {
        Log.d("TAG observer","ret2???");

        // WriteGPIO("0");
        //Log.d("TAG observer",event.string);

        //String ret2;
        //ret2 = readFromFile();
        //String substring = ret2.substring(0,4);
        //int i = Integer.parseInt(substring);


        //	Log.d("TAG observer",substring);

        //Log.d("TAG observer","ret");
        //Log.d("TAG observer",path);
        //if (path == null) {
        //Log.d("TAG observer","null");
        //	return;
        //}

        //Log.d("TAG observer",FileObserver.ATTRIB);
        //a new file or subdirectory was created under the monitored directory

        if ((FileObserver.ALL_EVENTS & event)!=0) {
            //FileAccessLogStatic.accessLogMsg += absolutePath + "/" + path + " is created\n";
            Log.d("TAG all", String.valueOf(absolutePath));
            if (String.valueOf(absolutePath) =="/dev/input/event0") {
                new WriteGpio().WriteGPIO("30", "1");



            }
        }
        //a file or directory was opened
        if ((FileObserver.OPEN & event)!=0) {
            //FileAccessLogStatic.accessLogMsg += path + " is opened\n";
            Log.d("TAG open", String.valueOf(absolutePath));
        }
        //data was read from a file
        if ((FileObserver.ACCESS & event)!=0) {
            //FileAccessLogStatic.accessLogMsg += absolutePath + "/" + path + " is accessed/read\n";
            // Log.d("TAG access", String.valueOf(absolutePath));
        }
        //data was written to a file
        if ((FileObserver.MODIFY & event)!=0) {
            //FileAccessLogStatic.accessLogMsg += absolutePath + "/" + path + " is modified\n";
            Log.d("TAG modify", String.valueOf(absolutePath));
        }
        //someone has a file or directory open read-only, and closed it
        if ((FileObserver.CLOSE_NOWRITE & event)!=0) {
           // FileAccessLogStatic.accessLogMsg += path + " is closed\n";
        }
        //someone has a file or directory open for writing, and closed it
        if ((FileObserver.CLOSE_WRITE & event)!=0) {
           // FileAccessLogStatic.accessLogMsg += absolutePath + "/" + path + " is written and closed\n";
            Log.d("TAG cw", String.valueOf(absolutePath));
        }
        //[todo: consider combine this one with one below]
        //a file was deleted from the monitored directory
        if ((FileObserver.DELETE & event)!=0) {
            //for testing copy file
//			FileUtils.copyFile(absolutePath + "/" + path);
           /// FileAccessLogStatic.accessLogMsg += absolutePath + "/" + path + " is deleted\n";
        }
        //the monitored file or directory was deleted, monitoring effectively stops
        if ((FileObserver.DELETE_SELF & event)!=0) {
           // FileAccessLogStatic.accessLogMsg += absolutePath + "/" + " is deleted\n";
        }
        //a file or subdirectory was moved from the monitored directory
        if ((FileObserver.MOVED_FROM & event)!=0) {
          //  FileAccessLogStatic.accessLogMsg += absolutePath + "/" + path + " is moved to somewhere " + "\n";
        }
        //a file or subdirectory was moved to the monitored directory
        if ((FileObserver.MOVED_TO & event)!=0) {
           // FileAccessLogStatic.accessLogMsg += "File is moved to " + absolutePath + "/" + path + "\n";
        }
        //the monitored file or directory was moved; monitoring continues
        if ((FileObserver.MOVE_SELF & event)!=0) {
           // FileAccessLogStatic.accessLogMsg += path + " is moved\n";
        }
        //Metadata (permissions, owner, timestamp) was changed explicitly
        if ((FileObserver.ATTRIB & event)!=0) {
          //  FileAccessLogStatic.accessLogMsg += absolutePath + "/" + path + " is changed (permissions, owner, timestamp)\n";
            Log.d("TAG attrib", String.valueOf(absolutePath));
        }
    }
}
