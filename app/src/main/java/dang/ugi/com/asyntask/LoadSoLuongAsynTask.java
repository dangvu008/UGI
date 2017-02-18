package dang.ugi.com.asyntask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;

import dang.ugi.com.R;

/**
 * Created by DANG on 12/3/2016.
 */

public class LoadSoLuongAsynTask extends AsyncTask<Boolean,Boolean,Boolean> {
    Context context;
    EditText editTextSoLuongMon;
    boolean cong = true;
    public LoadSoLuongAsynTask(Context context) {
        this.context = context;
        editTextSoLuongMon = (EditText) ((Activity)context).findViewById(R.id.editText_danggoimon_soluong);
    }

    @Override
    protected Boolean doInBackground(Boolean... booleen) {
        publishProgress(booleen[0]);
        return booleen[0];
    }

    @Override
    protected void onProgressUpdate(Boolean... values) {
        int soluonghientai = Integer.parseInt(editTextSoLuongMon.getText().toString());
        int soluongsaukhitinh = 1;
        if (values[0]==cong){
            soluongsaukhitinh = soluonghientai + 1;
        }else{
            if (soluonghientai>=2){
                soluongsaukhitinh = soluonghientai - 1;
            }
        }
        editTextSoLuongMon.setText(String.valueOf(soluongsaukhitinh));
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
