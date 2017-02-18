package dang.ugi.com.asyntask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import dang.ugi.com.R;
import dang.ugi.com.model.Utils.FormatData;
import dang.ugi.com.presenter.ChiTietGoiMon.ImplChiTietGoiMonPresenter;

/**
 * Created by DANG on 12/10/2016.
 */

public class LoadTienThuaAsynTask extends AsyncTask<Float,Float,Float>{
    Context context;
    int param;
    EditText editTextTienPhatSinh, editTextTongTien, editTextTienMat, editTextTienThua;
    TextView textViewMaGoiMon;
    ImplChiTietGoiMonPresenter chiTietGoiMonPresenter;
    float tienphatsinh;
    float tienthem = 0, tongtien = 0, tienmat= 0, tienthua=0;
    int magoimon;
    public LoadTienThuaAsynTask(Context context) {
        this.context = context;
        editTextTienPhatSinh = (EditText) ((Activity) context).findViewById(R.id.editText_thanhtoan_tienphatsinh);
        editTextTongTien = (EditText) ((Activity) context).findViewById(R.id.editText_thanhtoan_tongtien);
        editTextTienMat = (EditText) ((Activity) context).findViewById(R.id.editText_thanhtoan_tienmat);
        editTextTienThua = (EditText) ((Activity) context).findViewById(R.id.editText_thanhtoan_tienthua);
        textViewMaGoiMon = (TextView) ((Activity)context).findViewById(R.id.textView_dialog_thanhtoan_magoimon);
        chiTietGoiMonPresenter = new ImplChiTietGoiMonPresenter(context);
        if (editTextTienPhatSinh.getText().length()==0){
            tienphatsinh = 0;
        }else{
            tienphatsinh = Float.parseFloat(editTextTienPhatSinh.getText().toString());
        }
        tongtien = Float.parseFloat(editTextTongTien.getText().toString());
        //  tienmat = float.parsefloat(editTextTienMat.getText().toString());
        // tienthua = float.parsefloat(editTextTienThua.getText().toString());
        magoimon = Integer.parseInt(textViewMaGoiMon.getText().toString());
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Float... values) {
        super.onProgressUpdate(values);
        float tien = values[0];
        editTextTienThua.setText(FormatData.formatMoneyVietNam(tien));
    }

    private void loadTien() {

    }

    @Override
    protected void onCancelled(Float floats) {
        super.onCancelled(floats);
    }

    @Override
    protected Float doInBackground(Float... tiens) {
        float tien = tiens[0];
            tienmat = tien;
            if (tiens.length == 0) {
                tienmat = tongtien;
            }
            tienmat = tien;
            if (tienmat > tongtien) {
                tienthua = tienmat - tongtien;
            } else {
                tienthua = 0;
            }

        tienthua = tienmat - tongtien;
        publishProgress(tienthua);
        return tienthua;
    }

    @Override
    protected void onPostExecute(Float tiens) {
        super.onPostExecute(tiens);
    }
}
