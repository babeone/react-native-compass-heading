
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

    //degreeValue
    private int currentDegree = 0;
    // device sensor manager
    private SensorManager mSensorManager;

    @Override
    public void onSensorChanged( SensorEvent event ) {
       this.currentDegree = Math.round(event.values[0]);
    }


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

  /**
    Method for init the DeviceSensor. Return a promise, get resolved when the sensor is active
   */
  @ReactMethod
    public void initSensor(Promise promise) {
        mSensorManager = (SensorManager) reactContext.getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
        SensorManager.SENSOR_DELAY_GAME);
        promise.resolve(true);
    }

    /**
      Method for get the Distance in Degree from the true North. Return a promise, get resolved with a number (degree)
     */
    @ReactMethod
    public void getHeading(Promise promise) {
        //return the currentDegree
        try {
            Thread.sleep(500);
            promise.resolve(currentDegree);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

}