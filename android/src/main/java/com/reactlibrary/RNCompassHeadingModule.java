
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
    private Sensor mAccelerometer;
    private Sensor mMagnetometer;

    boolean haveAccelerometer = false;
    boolean haveMagnetometer = false;

    float[] gData = new float[3]; // accelerometer
    float[] mData = new float[3]; // magnetometer
    float[] rMat = new float[9];
    float[] iMat = new float[9];
    float[] orientation = new float[3];

    @Override
    public void onSensorChanged( SensorEvent event ) {
        float[] data;
        switch ( event.sensor.getType() ) {
            case Sensor.TYPE_ACCELEROMETER:
                gData = event.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mData = event.values.clone();
                break;
            default: return;
        }

        if ( SensorManager.getRotationMatrix( rMat, iMat, gData, mData ) ) {
            this.currentDegree= (int) ( Math.toDegrees( SensorManager.getOrientation( rMat, orientation )[0] ) + 360 ) % 360;
        }
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

    @ReactMethod
    public void initSensor(Promise promise) {
        mSensorManager = (SensorManager) reactContext.getSystemService(Context.SENSOR_SERVICE);
        this.mAccelerometer = this.mSensorManager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER );
        this.haveAccelerometer = this.mSensorManager.registerListener( this, this.mAccelerometer, SensorManager.SENSOR_DELAY_GAME );
        this.mMagnetometer = this.mSensorManager.getDefaultSensor( Sensor.TYPE_MAGNETIC_FIELD );
        this.haveMagnetometer = this.mSensorManager.registerListener( this, this.mMagnetometer, SensorManager.SENSOR_DELAY_GAME );

        promise.resolve(true);
    }

    @ReactMethod
    public void getHeading(Promise promise) {
        //return the currentDegree
        try {
            Thread.sleep(300);
            promise.resolve(currentDegree);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

}