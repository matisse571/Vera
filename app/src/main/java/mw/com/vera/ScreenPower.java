package mw.com.vera;

/**
 * Created by Mark on 3/5/17.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class ScreenPower extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
       // new WriteGpio().WriteGPIO("30", "0");
       // Log.d("LOG", "Turn Screen Off" );
        //Note: If your alarm has to perform network task, then start a download service inside onRecieve() method of your alarm broadcast.
    }
}

