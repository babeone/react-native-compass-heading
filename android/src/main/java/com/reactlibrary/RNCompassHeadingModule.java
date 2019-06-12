
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.Context;
import com.facebook.react.bridge.Promise;

public class RNCompassHeadingModule extends ReactContextBaseJavaModule implements SensorEventListener{

    private final ReactApplicationContext reactContext;

    // device sensor manager
    private SensorManager mSensorManager;

    //degreeValue
    private float currentDegree = 0;

    public RNCompassHeadingModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNCompassHeading";
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){}

    @Override
    public void onSensorChanged(SensorEvent event) {

        // get the angle around the z-axis rotated
        currentDegree = Math.round(event.values[0]);

        //stop register event, after updated it the first time, so i have the value stored one time
        mSensorManager.unregisterListener(this);
    }

    @ReactMethod
    public void initSensor(Promise promise) {
        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager) reactContext.getSystemService(Context.SENSOR_SERVICE);
        promise.resolve(true);
    }

    @ReactMethod
    public void getHeading(Promise promise) {
        //return the currentDegree
        promise.resolve(currentDegree);
    }

}