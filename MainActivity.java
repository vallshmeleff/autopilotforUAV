package com.example.pressuremonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

//------------------------------------------------
//
// Application for working with Android sensors: gyroscope,
// accelerometer and magnetometer. Simultaneously works with
// three sensors. Shows a list of available sensors
//
// Sensor values are written to Log
// The application was debugged on a real smartphone connected via a USB cable
//
// (c) by Valery Shmelev (Oflameron) https://www.linkedin.com/in/valery-shmelev-479206227/
// GitHUB Repository  https://github.com/vallshmeleff
//
//------------------------------------------------

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    public static float gravity0;
    public static float gravity1;
    public static float gravity2;
    public static float  gyro0;
    public static float  gyro1;
    public static float  gyro2;
    public static float geomagnetic0;
    public static float geomagnetic1;
    public static float geomagnetic2;
    //-----------------------------------------------------
    //
    // We use Android sensors. Get a list of sensors and data from the gyroscope,
    // accelerometer and magnetometer. The results will be written to Log
    //
    // (c) by Valery Shmelev (Oflameron) https://www.linkedin.com/in/valery-shmelev-479206227/
    // GitHUB Repository  https://github.com/vallshmeleff
    //
    //-----------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        //-------------------------------------------------
        // List of sensors
        //-------------------------------------------------
        List<String> listSensorType = new ArrayList<>();
        for (int i = 0; i < deviceSensors.size(); i++) {
            listSensorType.add(deviceSensors.get(i).getName());
            Log.i("Sensores", " == == Sensor List == == " + deviceSensors.get(i).getName().toString());

        }



        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);

        Sensor magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);

        Log.i("Sensor", " == == Registered == == ");
    } // OnCreate

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            //         if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            gyro0 = event.values[0];
            gyro1 = event.values[1];
            gyro2 = event.values[2];
            Log.i("GYROSCOPE", " == == Gyro Channel 0 == == " + gyro0);
            Log.i("GYROSCOPE", " == == Gyro Channel 1 == == " + gyro1);
            Log.i("GYROSCOPE", " == == Gyro Channel 2 == == " + gyro2);
        }
        if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            // if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gravity0 = event.values[0];
            gravity1 = event.values[1];
            gravity2 = event.values[2];
            Log.i("GRAVITY", " == == Gravity Channel 0 == == " + gravity0);
            Log.i("GRAVITY", " == == Gravity Channel 1 == == " + gravity1);
            Log.i("GRAVITY", " == == Gravity Channel 2 == == " + gravity2);
        }
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            // if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            geomagnetic0 = event.values[0];
            geomagnetic1 = event.values[1];
            geomagnetic2 = event.values[2];
            Log.i("MAGNETIC", " == == Geomagnetic Channel 0 == == " + geomagnetic0);
            Log.i("MAGNETIC", " == == Geomagnetic Channel 1 == == " + geomagnetic1);
            Log.i("MAGNETIC", " == == Geomagnetic Channel 2 == == " + geomagnetic2);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.i("SENSORS", " Accuracy " + accuracy);
    }
}