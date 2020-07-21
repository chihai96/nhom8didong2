package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private SensorManager sm;
    private LinearLayout myLayOut = null;
    private ImageView mouse = null;
    private CustomButton btnchamCong, btnthemNV, btndsCC;
    private TextView txttest;

    private float x;
    private float y;

    private float acelVal;
    private float acelLast;
    private float shake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomButton btnchamCong = (CustomButton)findViewById(R.id.btnChamCong);
        CustomButton btnthemNV = (CustomButton)findViewById(R.id.btnThemNV);
        CustomButton btndsCC = (CustomButton)findViewById(R.id.btnDSCC);
        txttest = (TextView)findViewById(R.id.txtTest);

        btnchamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"click",Toast.LENGTH_SHORT).show();
            }
        });

        myLayOut = (LinearLayout) findViewById(R.id.MyLayout);
        mouse = (ImageView) findViewById(R.id.Mouse);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(sensorListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;



        myLayOut.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = event.getX();
                y = event.getY();

                if(event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    mouse.setX(x);
                    mouse.setY(y);
                }


                return true;
            }
        });
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            acelLast = acelVal;
            acelVal = (float) Math.sqrt((double) (x*x + y*y + z*z));

            float delta = acelVal - acelLast;
            shake = shake * 0.9f + delta;

            if(shake > 12)
            {
                myLayOut.setBackgroundResource(R.drawable.backgroud_dark);
                txttest.setTextColor(Color.WHITE);
            }
        }



        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
