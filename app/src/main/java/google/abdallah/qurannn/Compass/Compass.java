package google.abdallah.qurannn.Compass;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Compass implements SensorEventListener {
    Sensor asensore;
    Sensor gsensore;
    SensorManager sensorManager;
    private float azimuthFix;
    public interface CompassListener {
        void onNewAzimuth(float azimuth);
    }

    private CompassListener listener;
    private float[] aData = new float[3];
    private float[] mData = new float[3];
    private float[] R = new float[9];
    private float[] I = new float[9];
    public Compass(Context context){
        sensorManager= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        asensore=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gsensore=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }
    public  void start(Context context){
        sensorManager.registerListener(this,asensore,SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this,gsensore,SensorManager.SENSOR_DELAY_FASTEST);

        PackageManager manager=context.getPackageManager();
        boolean haveAS = manager.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER);
        boolean haveCS = manager.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS);
        if (!haveAS || !haveCS) {
            sensorManager.unregisterListener(this, asensore);
            sensorManager.unregisterListener(this, gsensore);
            Log.e(TAG, "Device don't have enough sensors");

            dialogError(context);
        }
    }

    private void dialogError(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error");
        builder.setCancelable(false);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage("dialog_message_sensor_not_exist");
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (context instanceof Activity)
                    ((Activity) context).finish();
            }
        });
        builder.create().show();
    }
    public void stop() {
        sensorManager.unregisterListener(this);
    }
    public void setAzimuthFix(float fix) {
        azimuthFix = fix;
    }
    public void resetAzimuthFix() {
        setAzimuthFix(0);
    }
    public void setListener(CompassListener l) {
        listener = l;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        final float alpha = 0.97f;
        //boolean hasAS = false, hasMS = false;

        synchronized (this) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                aData[0] = alpha * aData[0] + (1 - alpha)
                        * event.values[0];
                aData[1] = alpha * aData[1] + (1 - alpha)
                        * event.values[1];
                aData[2] = alpha * aData[2] + (1 - alpha)
                        * event.values[2];
            }
            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                mData[0] = alpha * mData[0] + (1 - alpha)
                        * event.values[0];
                mData[1] = alpha * mData[1] + (1 - alpha)
                        * event.values[1];
                mData[2] = alpha * mData[2] + (1 - alpha)
                        * event.values[2];
            }
            boolean success = SensorManager.getRotationMatrix(R, I, aData, mData);
            if (success) {
                float[] orientation = new float[3];
                SensorManager.getOrientation(R, orientation);
                float azimuth = (float) Math.toDegrees(orientation[0]); // orientation
                azimuth = (azimuth + azimuthFix + 360) % 360;
                //azimuth = (azimuth + azimuthFix + 360) % 294;

                // Log.d(TAG, "azimuth (deg): " + azimuth);
                if (listener != null) {
                    listener.onNewAzimuth(azimuth);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
