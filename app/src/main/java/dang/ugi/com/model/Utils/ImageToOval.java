package dang.ugi.com.model.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.ImageView;

/**
 * Created by DANG on 3/24/2017.
 */

public class ImageToOval {

    private Bitmap bmp;
    private ImageView img;

    public ImageToOval(Bitmap bmp, ImageView img) {

        this.bmp = bmp;
        this.img = img;
        onDraw();
    }


    private void onDraw() {

        Canvas canvas = new Canvas();
        if (bmp.getWidth() == 0 || bmp.getHeight() == 0) {
            return;
        }

        int w = bmp.getWidth(), h = bmp.getHeight();

        Bitmap roundBitmap = getOvalCroppedBitmap(bmp, w);

        img.setImageBitmap(roundBitmap);

    }


    public static Bitmap getOvalCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius,
                    false);
        else
            finalBitmap = bitmap;
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
                finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(),
                finalBitmap.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        RectF oval = new RectF(0, 0, 120, 120);
        canvas.drawOval(oval, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, oval, paint);

        return output;
    }

}