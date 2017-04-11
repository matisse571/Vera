package mw.com.vera;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.util.Calendar;

/**
 * Created by Mark on 3/6/17.
 */

public class SetAlarm extends Activity{

    private PendingIntent pendingIntent;
    public Context context;



    public void alarm(int interval) {
        Intent alarmIntent = new Intent(SetAlarm.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        Log.d("LOG", "start");
        //AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Log.d("LOG", "startb");


        // manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (8000), pendingIntent);

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }
}