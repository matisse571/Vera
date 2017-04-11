package mw.com.vera;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONObject;



import android.app.Activity;
import mw.com.vera.NativeGpio.GpioInterruptCallback;
import mw.com.vera.NativeGpio.GpioInterruptCallback8;






    public class MainActivity extends Activity {
        
        private Button btn1;
        private Button btn2;
        private Button btn3;
        private Button btn4;
        private Button btn5;
        private Button btn6;
        private Button btn7;
        private Button btn8;

        public  TextView cityField;
        public  TextView updatedField;
        public  TextView detailsField;
        public  TextView currentTemperatureField;
        TextView windSpeed;
        ImageView imageView;




        private ProgressDialog pDialog;
        private ListView lv;
        JSONObject json2;



        private void renderWeather2(JSONObject json) {

            try {



                cityField.setText(json.getString("name").toUpperCase(Locale.US) +
                        ", " +
                        json.getJSONObject("sys").getString("country"));



                JSONObject details = json.getJSONArray("weather").getJSONObject(0);

                JSONObject main = json.getJSONObject("main");
                JSONObject wind = json.getJSONObject("wind");


                detailsField.setText(
                        details.getString("description").toUpperCase(Locale.US) +
                                "\n" + "Humidity: " + main.getString("humidity") + "%" +
                                "\n" + "Pressure: " + main.getString("pressure") + " hPa");

                currentTemperatureField.setText(
                        String.format("%.2f", main.getDouble("temp")) + " F");

                DateFormat df = DateFormat.getDateTimeInstance();
                String updatedOn = df.format(new Date(json.getLong("dt") * 1000));
                updatedField.setText(updatedOn);
                windSpeed.setText( wind.getString("speed") + " MPH " + wind.getString("deg") + " DEG");



            setWeatherIcon(details.getInt("id"),
                    json.getJSONObject("sys").getLong("sunrise") * 1000,
                    json.getJSONObject("sys").getLong("sunset") * 1000);

            } catch (Exception e) {
                Log.e("SimpleWeather", "One or more fields not found in the JSON data");
            }
        }
        private void setWeatherIcon(int actualId, long sunrise, long sunset) {
            int id = actualId / 100;
            Log.e("id", Integer.toString(actualId));
            Log.e("id", Integer.toString(id));
            String icon = "";
            if (actualId == 800) {
                long currentTime = new Date().getTime();
                if (currentTime >= sunrise && currentTime < sunset) {
                   // icon = getString(R.string.weather_sunny);
                } else {
                   // icon = getString(R.string.weather_clear_night);
                }
            } else {
                switch (id) {
                    case 2:
                       // icon = getString(R.string.weather_thunder);
                        break;
                    case 3:
                      //  icon = getString(R.string.weather_drizzle);
                        break;
                    case 7:
                      //  icon = getString(R.string.weather_foggy);
                        break;
                    case 8:
                      //  icon = getString(R.string.weather_cloudy);
                        break;
                    case 6:
                        //icon = getString(R.string.weather_snowy);
                        break;
                    case 5:
                        //icon = getString(R.string.weather_rainy);
                        imageView.setImageResource(R.drawable.rainnight);
                        //imageView.setImageResource(R.drawable.
                        break;
                }
            }
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            setContentView(R.layout.activity_main);

            cityField = (TextView) findViewById(R.id.city_field);
            updatedField = (TextView) findViewById(R.id.updated_field);
            detailsField = (TextView) findViewById(R.id.details_field);
           currentTemperatureField = (TextView) findViewById(R.id.current_temperature_field);
            windSpeed = (TextView) findViewById(R.id.windSpeed);
            imageView = (ImageView) findViewById(R.id.image);


//cityField.setText();

            btn1 = (Button) findViewById(R.id.btn_first);
            btn2 = (Button) findViewById(R.id.btn_second);
            btn3 = (Button) findViewById(R.id.btn_third);
            btn4 = (Button) findViewById(R.id.btn_forth);
            btn5 = (Button) findViewById(R.id.btn_fifth);
            btn6 = (Button) findViewById(R.id.btn_sixth);
            btn7 = (Button) findViewById(R.id.btn_seventh);
            btn8 = (Button) findViewById(R.id.btn_eighth);
            int screenWidth = 480;
            btn1.setWidth(screenWidth / 2);

            btn1.setOnClickListener(new View.OnClickListener() {
                //@Override
                public void onClick(View v) {


                    new SendScene().sendScene("1");

                }
            });
            btn2.setWidth(screenWidth / 2);
            btn2.setOnClickListener(new View.OnClickListener() {
                //@Override
                public void onClick(View v) {
                    new SendScene().sendScene("2");


                }
            });
            btn3.setWidth(screenWidth / 2);
            btn3.setOnClickListener(new View.OnClickListener() {
                //@Override
                public void onClick(View v) {
                    new SendScene().sendScene("3");

                }
            });
            btn4.setWidth(screenWidth / 2);
            btn4.setOnClickListener(new View.OnClickListener() {
                //@Override
                public void onClick(View v) {
                    new SendScene().sendScene("4");

                }
            });

            btn5.setWidth(screenWidth / 2);
            btn5.setOnClickListener(new View.OnClickListener() {
                //@Override
                public void onClick(View v) {
                    new SendScene().sendScene("5");

                }
            });

            btn6.setWidth(screenWidth / 2);
            btn6.setOnClickListener(new View.OnClickListener() {
                //@Override
                public void onClick(View v) {
                    new SendScene().sendScene("6");

                }
            });

            btn7.setWidth(screenWidth / 2);
            btn7.setOnClickListener(new View.OnClickListener() {
                //@Override
                public void onClick(View v) {
                    new SendScene().sendScene("7");
                   // new WriteGpio().WriteGPIO("30", "0");


                }
            });


            btn8.setWidth(screenWidth / 2);
            btn8.setOnClickListener(new View.OnClickListener() {
                //@Override
                public void onClick(View v) {
                    new SendScene().sendScene("8");
                    //cityField.setText("i");
                   // renderWeather("r");
                  //  new ClassB(TextView.Update());

                }
            });



            start();

          new GetWeather().execute();
            if(sGpioThread7 == null){
                sGpioThread7 = new Thread(mGpioRunnable7, "GPIO-Thread7");
                Log.d("thread7","start");
                sGpioThread7.start();

            }
            if(sGpioThread8 == null){
                sGpioThread8 = new Thread(mGpioRunnable8, "GPIO-Thread8");
                Log.d("thread8","start");
                sGpioThread8.start();

            }
        }


        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                new GetWeather().execute();
                // internet lost alert dialog method call from here...
            }
        };
       private static final AtomicInteger sCurrentValue = new AtomicInteger(0);
        private static Thread sGpioThread7;
        private static Thread sGpioThread8;

        private final Runnable mGpioRunnable7 = new Runnable() {

            @Override
            public void run() {
                while(true){

                    NativeGpio.readGpio("/sys/class/gpio/gpio7/value", new GpioInterruptCallback(){
                        @Override
                        public void onNewValue(int value) {
                            Log.d("ThreadUtils 7", getThreadSignature());
                            String  threadName = getThreadName();
                            Log.d("thread name",threadName);

                            if (threadName.equals("GPIO-Thread7")) {

                                Log.d("thread 7", "yes");
                               // Log.d("thread7= %d", String.valueOf(value));
                                new SendScene().sendScene("7");
                            }
                            if (threadName.equals("GPIO-Thread8")) {

                                Log.d("thread 8", "yes");
                               // Log.d("thread7= %d", String.valueOf(value));
                                new SendScene().sendScene("1");
                            }
                            if (value == 1) {
                                Log.d("thread7","send");
                               // new SendScene().sendScene("7");

                            }


                            sCurrentValue.set(value);
                          //  updateView();
                        }
                    });

                }
            }
        };
        private final Runnable mGpioRunnable8 = new Runnable() {
            @Override
            public void run() {
                while(true){

                    NativeGpio.readGpio("/sys/class/gpio/gpio8/value", new GpioInterruptCallback8(){
                        @Override
                        public void onNewValue(int value) {
                            Log.d("ThreadUtils 8", getThreadSignature());
                          String  threadName = getThreadName();
                            Log.d("thread name",threadName);

                            if (threadName.equals("GPIO-Thread7")) {

                                Log.d("thread 7", "yes");
                               // Log.d("thread7= %d", String.valueOf(value));
                                new SendScene().sendScene("7");
                            }
                            if (threadName.equals("GPIO-Thread8")) {

                                Log.d("thread 8", "yes");
                               // Log.d("thread7= %d", String.valueOf(value));
                                new SendScene().sendScene("1");
                            }
                            if (value == 1) {
                                Log.d("thread8","send");
                              //  new SendScene().sendScene("1");

                            }
                            if (value == 0) {
                                Log.d("thread8","off");
                               // new SendScene().sendScene("7");

                            }
                           // sCurrentValue.set(value);
                            //  updateView();
                        }
                    });
                }
            }
        };
        public static String getThreadName()
        {
            Thread t = Thread.currentThread();

            String name = t.getName();

            return (name);
        }
        public static String getThreadSignature()
        {
            Thread t = Thread.currentThread();
            long l = t.getId();
            String name = t.getName();
            long p = t.getPriority();
            String gname = t.getThreadGroup().getName();
            return (name
                    + ":(id)" + l
                    + ":(priority)" + p
                    + ":(group)" + gname);
        }
        public void start () {
          //  registerReceiver(broadcastReceiver, new IntentFilter("INTERNET_LOST"));

            Log.d("LOG", "start");
            PendingIntent pendingIntent;
            //Intent alarmIntent = new Intent(this, MyBroadcastReceiver.class);
            //Intent alarmIntent = new Intent(this, MyBroadcastReceiver.class);

            Intent alarmIntent = new Intent(this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1800000, pendingIntent);
            registerReceiver(broadcastReceiver, new IntentFilter("alarmIntent"));
        }

        public class GetWeather extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Showing progress dialog
                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setMessage("Weather Update");
                pDialog.setCancelable(false);
                pDialog.show();

            }

            @Override
            protected Void doInBackground(Void... arg0) {
                //JSONObject json2 = RemoteFetch.getJSON("Sonoma");
                json2 = RemoteFetch.getJSON(getString(R.string.city),getString(R.string.open_weather_maps_app_id ));
                // renderWeather2(json);
                // HttpHandler sh = new HttpHandler();

                // Making a request to url and getting response
                ///  String jsonStr = sh.makeServiceCall(url);

                // Log.e(TAG, "Response from url: " + json);





                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                // Dismiss the progress dialog
                if (pDialog.isShowing())
                    pDialog.dismiss();
                /**
                 * Updating parsed JSON data into ListView
                 * */
                renderWeather2(json2);
            }

        }


        }






