package com.java2blog.getbatterytemperatureapp;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.java2blog.getbatterytemperatureapp.R;

public class MainActivity extends Activity {

    TextView tempTextView;
    Button button;
    IntentFilter intentfilter;
    float batteryTemp;
    String currentBatterytemp="Current Battery temp :";
    int batteryLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.buttonBatteryTemp);
        tempTextView = (TextView)findViewById(R.id.textViewBatteryTemp);

        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.this.registerReceiver(broadcastreceiver,intentfilter);

            }
        });

    }

    private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            batteryTemp = (float)(intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0))/10;

            tempTextView.setText(currentBatterytemp +" "+batteryTemp +" "+ (char) 0x00B0 +"C");

        }
    };


}