package org.libsdl.app;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/* JADX INFO: compiled from: SDLControllerManager.java */
/* JADX INFO: loaded from: classes.dex */
class SDLJoySensorListener implements SensorEventListener {
    int device_id;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public SDLJoySensorListener(int i) {
        this.device_id = i;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        SDLControllerManager.onNativeJoySensor(this.device_id, sensorEvent.sensor.getType(), sensorEvent.timestamp, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
    }
}
