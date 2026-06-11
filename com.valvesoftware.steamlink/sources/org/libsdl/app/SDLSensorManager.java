package org.libsdl.app;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.Log;
import java.util.ConcurrentModificationException;

/* JADX INFO: loaded from: classes.dex */
class SDLSensorManager {
    static final int RETRY_COUNT = 3;
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
        boolean z = false;
        int i2 = 0;
        while (!z) {
            try {
                sensorManager.registerListener(sensorEventListener, sensor, i, (Handler) null);
            } catch (ConcurrentModificationException unused) {
                i2++;
                if (i2 <= 3) {
                    try {
                        Thread.sleep(1L);
                    } catch (Exception unused2) {
                    }
                } else {
                    Log.v("SDL", "Multiple ConcurrentModificationException caught while registering sensor listener, canceling operation");
                }
            }
            z = true;
        }
    }

    private synchronized void UnregisterListener(SensorManager sensorManager, SensorEventListener sensorEventListener, Sensor sensor) {
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                sensorManager.unregisterListener(sensorEventListener, sensor);
            } catch (ConcurrentModificationException unused) {
                i++;
                if (i <= 3) {
                    try {
                        Thread.sleep(1L);
                    } catch (Exception unused2) {
                    }
                } else {
                    Log.v("SDL", "Multiple ConcurrentModificationException caught while unregistering sensor listener, canceling operation");
                }
            }
            z = true;
        }
    }
}
