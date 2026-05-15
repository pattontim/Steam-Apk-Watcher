package org.libsdl.app;

import android.os.Build;
import android.view.InputDevice;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
public class SDLControllerManager {
    private static final String TAG = "SDLControllerManager";
    protected static SDLHapticHandler mHapticHandler;
    protected static SDLJoystickHandler mJoystickHandler;

    static native void nativeAddHaptic(int i, String str);

    static native void nativeAddJoystick(int i, String str, String str2, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, boolean z2, boolean z3, boolean z4);

    static native void nativeRemoveHaptic(int i);

    static native void nativeRemoveJoystick(int i);

    static native void nativeSetupJNI();

    static native void onNativeHat(int i, int i2, int i3, int i4);

    static native void onNativeJoy(int i, int i2, float f);

    static native void onNativeJoySensor(int i, int i2, long j, float f, float f2, float f3);

    public static native boolean onNativePadDown(int i, int i2, int i3);

    public static native boolean onNativePadUp(int i, int i2, int i3);

    static void initialize() {
        if (mJoystickHandler == null) {
            mJoystickHandler = new SDLJoystickHandler();
        }
        if (mHapticHandler == null) {
            if (Build.VERSION.SDK_INT >= 31) {
                mHapticHandler = new SDLHapticHandler_API31();
            } else if (Build.VERSION.SDK_INT >= 26) {
                mHapticHandler = new SDLHapticHandler_API26();
            } else {
                mHapticHandler = new SDLHapticHandler();
            }
        }
    }

    public static boolean handleJoystickMotionEvent(MotionEvent motionEvent) {
        return mJoystickHandler.handleMotionEvent(motionEvent);
    }

    static void pollInputDevices() {
        mJoystickHandler.pollInputDevices();
    }

    static void joystickSetLED(int i, int i2, int i3, int i4) {
        mJoystickHandler.setLED(i, i2, i3, i4);
    }

    static void joystickSetSensorsEnabled(final int i, final boolean z) {
        SDL.getContext().runOnUiThread(new Runnable() { // from class: org.libsdl.app.SDLControllerManager.1
            @Override // java.lang.Runnable
            public void run() {
                SDLControllerManager.mJoystickHandler.setSensorsEnabled(i, z);
            }
        });
    }

    static void pollHapticDevices() {
        mHapticHandler.pollHapticDevices();
    }

    static void hapticRun(int i, float f, int i2) {
        mHapticHandler.run(i, f, i2);
    }

    static void hapticRumble(int i, float f, float f2, int i2) {
        mHapticHandler.rumble(i, f, f2, i2);
    }

    static void hapticStop(int i) {
        mHapticHandler.stop(i);
    }

    public static boolean isDeviceSDLJoystick(int i) {
        InputDevice device = InputDevice.getDevice(i);
        if (device == null || i < 0) {
            return false;
        }
        int sources = device.getSources();
        return (sources & 16) != 0 || (sources & 513) == 513 || (sources & 1025) == 1025;
    }
}
