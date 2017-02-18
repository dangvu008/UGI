package dang.ugi.com.asyntask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.ChiTietGoiMon;
import dang.ugi.com.model.Utils.FormatData;
import dang.ugi.com.presenter.ChiTietGoiMon.ImplChiTietGoiMonPresenter;

/**
 * Created by DANG on 12/5/2016.
 */

public class LoadTinhTienAsynTask extends AsyncTask<Float, List<Float>, List<Float>> {
    Context context;
    int param;
    EditText editTextTienPhatSinh, editTextTongTien, editTextTienMat, editTextTienThua;
    TextView textViewMaGoiMon;
    ImplChiTietGoiMonPresenter chiTietGoiMonPresenter;
    float tienphatsinh;
    float tienthem = 0, tongtien = 0, tienmat= 0, tienthua=0;
    int magoimon;
    public LoadTinhTienAsynTask(Context context, int param) {
        this.context = context;
        this.param = param;
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
        //tongtien = Float.parseFloat(editTextTongTien.getText().toString());
      //  tienmat = Float.parseFloat(editTextTienMat.getText().toString());
       // tienthua = Float.parseFloat(editTextTienThua.getText().toString());
        magoimon = Integer.parseInt(textViewMaGoiMon.getText().toString());
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(List<Float>... values) {
        super.onProgressUpdate(values);
        List<Float> list = values[0];

        if (param == 1){
            editTextTienMat.setText(FormatData.formatMoneyVietNam(list.get(2)));
            editTextTongTien.setText(FormatData.formatMoneyVietNam(list.get(1)));
            editTextTienThua.setText(FormatData.formatMoneyVietNam(list.get(3)));
        }else if(param==2){
            editTextTienThua.setText(FormatData.formatMoneyVietNam(list.get(3)));
        }

    }

    private void loadTien() {

    }

    @Override
    protected void onCancelled(List<Float> Floats) {
        super.onCancelled(Floats);
    }

    @Override
    protected List<Float> doInBackground(Float... tiens) {

        List<Float> listtien = new ArrayList<>();
        List<ChiTietGoiMon> listChitietgoimon = chiTietGoiMonPresenter.listChiTietGoiMon(magoimon);
        for(int i=0;i<listChitietgoimon.size();i++){
            tongtien += listChitietgoimon.get(i).getThanhTien();
        }
        float tien = tiens[0];

        if (param == 1) {
            if (tien>0 && tien%100 !=0){
                tien = tien * 100;
            }
            if (tiens.length == 0) {
                tienphatsinh = 0;
            }
            tienthem = tien;
            tienmat = tongtien;
        }
        if (param == 2) {
            tienthem = tienphatsinh;
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

        }
        tongtien = tongtien + tienthem;
        float vat = (float) (tongtien * 0.1);
        tongtien = tongtien + vat;
        tienthua = tienmat - tongtien;
        listtien.add(tienphatsinh);
        listtien.add(tongtien);
        listtien.add(tienmat);
        listtien.add(tienthua);
        publishProgress(listtien);
        return listtien;
    }

    @Override
    protected void onPostExecute(List<Float> tiens) {
        super.onPostExecute(tiens);
    }
}
