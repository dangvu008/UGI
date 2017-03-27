package dang.ugi.com.asyntask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import dang.ugi.com.model.Utils.ImageToOval;
import dang.ugi.com.model.Utils.ResizeImage;

/**
 * Created by DANG on 3/24/2017.
 */

public class DownloadImage extends AsyncTask<String,Bitmap,Bitmap> {
    ImageView imageView;
    Bitmap bimage =null;
    public DownloadImage(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];

        try {
            InputStream in = new java.net.URL(url).openStream();
            bimage = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            Log.e("loi",e.getMessage());
        }
        return bimage;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        Bitmap bitmapResized = ResizeImage.scaleDown(bitmap,100,false);
        imageView.setImageBitmap(ImageToOval.getOvalCroppedBitmap(bitmapResized,120));
    }
}
