package dang.ugi.com.model.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DANG on 9/10/2016.
 */
public class CheckIsFirstRun {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private int MODE_PRIVATE = 0;
    public static final String PREF_NAME = "UgiPrefCheckIsFirstRun";
    public static final String KEY_FIRST = "firstrun";
    Context context;

    public CheckIsFirstRun(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,MODE_PRIVATE);
    }
    public boolean onResumePref(){
        boolean check = false;
        if (sharedPreferences.getBoolean(KEY_FIRST,true)){
            sharedPreferences.edit().putBoolean(KEY_FIRST,false).commit();
            check = true;
        }
        return check;
    }
}
