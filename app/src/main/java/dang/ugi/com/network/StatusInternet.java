package dang.ugi.com.network;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by DANG on 1/3/2017.
 */

public class StatusInternet extends Application {
   private static StatusInternet mInstance;
    Context context;

    public StatusInternet(Context context) {
        this.context = context;
        mInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public static synchronized StatusInternet getInstance(){
        return mInstance;
    }

    public static boolean isConnected() throws InterruptedException, IOException
    {
        String command = "ping -c 1 google.com";
        return (Runtime.getRuntime().exec (command).waitFor() == 0);
    }


    public void showSnackBar(View view) {
        boolean isConnected= false;
        try {
            isConnected = isConnected();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message;
        int color;
        if (isConnected){
            message ="Internet Connected !";
            color = Color.GREEN;
        }else{
            message ="Internet not Connected !";
            color = Color.RED;
        }
        Snackbar snackbar = Snackbar.make(view,message,Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }
}
