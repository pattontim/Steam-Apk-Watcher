package org.libsdl.app;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
class SDLSensorManager {
    private static SDLSensorManager mManager = new SDLSensorManager();

    SDLSensorManager() {
    }

    public static void registerListener(SensorManager sensorManager, SensorEventListener sensorEventListener, Sensor sensor, int i) {
        mManager.RegisterListener(sensorManager, sensorEventListener, sensor, i);
    }

    public static void unregisterListener(SensorManager sensorManager, SensorEventListener sensorEventListener, Sensor sensor) {
        mManager.UnregisterListener(sensorManager, sensorEventListener, sensor);
    }

    private synchronized void RegisterListener(SensorManager sensorManager, SensorEventListener sensorEventListener, Sensor sensor, int i) {
        sensorManager.registerListener(sensorEventListener, sensor, i, (Handler) null);
    }

    private synchronized void UnregisterListener(SensorManager sensorManager, SensorEventListener sensorEventListener, Sensor sensor) {
        sensorManager.unregisterListener(sensorEventListener, sensor);
    }
}
