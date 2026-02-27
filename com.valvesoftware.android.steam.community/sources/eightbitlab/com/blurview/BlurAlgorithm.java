package eightbitlab.com.blurview;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/* JADX INFO: loaded from: classes3.dex */
public interface BlurAlgorithm {
    Bitmap blur(Bitmap bitmap, float f);

    boolean canModifyBitmap();

    void destroy();

    Bitmap.Config getSupportedBitmapConfig();

    void render(Canvas canvas, Bitmap bitmap);

    float scaleFactor();
}
