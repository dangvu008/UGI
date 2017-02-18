package dang.ugi.com.asyntask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.ChiTietGoiMon;
import dang.ugi.com.model.Entities.GoiMon;
import dang.ugi.com.model.Utils.FormatData;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.ChiTietGoiMon.ImplChiTietGoiMonPresenter;
import dang.ugi.com.presenter.GoiMon.ImplGoiMonPresenter;

/**
 * Created by DANG on 11/21/2016.
 */

public class LoadTongTienGoiMonAsyntask extends AsyncTask<Integer,Integer,Integer>{
    private List<ChiTietGoiMon> chiTietGoiMonList;
    private Context context;
    private ImplChiTietGoiMonPresenter implChiTietGoiMonPresenter;
    private ImplGoiMonPresenter implGoiMonPresenter;
    private int maCuaHang;
    private GoiMon goiMon;
    private float tienthem;
    private EditText editTextTienThem;
    private TextView textViewTongTien;
    public LoadTongTienGoiMonAsyntask(Context context) {
        this.context = context;
        implChiTietGoiMonPresenter = new ImplChiTietGoiMonPresenter(context);
        implGoiMonPresenter = new ImplGoiMonPresenter(context);
        maCuaHang = PrefNhaHang.layCuaHangHienTai(context).getMaCuaHang();
        loadControls(context);
    }

    private void loadControls(Context context) {
        editTextTienThem = (EditText) ((Activity)context).findViewById(R.id.editText_danggoimon_tienThem);
        textViewTongTien = (TextView) ((Activity)context).findViewById(R.id.textview_DangGoiMon_TongTien);
    }


    /**
     * param 0 load recyclerview
     * */
    @Override
    protected Integer doInBackground(Integer... integers) {
        int magoimon = integers[0];
        publishProgress(magoimon);
        return magoimon;
    }


    @Override
    protected void onProgressUpdate(Integer... magoimons) {
        super.onProgressUpdate(magoimons);
        chiTietGoiMonList =implChiTietGoiMonPresenter.listChiTietGoiMon(magoimons[0]);
        if (chiTietGoiMonList!=null){
            loadToTextview(chiTietGoiMonList);
        }

    }

    private void loadToTextview(List<ChiTietGoiMon> listChiTietGoiMon) {
        float tongtien = 0;
        if (listChiTietGoiMon==null){
            tongtien = 0;
        }else{
            for(int i=0;i<listChiTietGoiMon.size();i++){
                tongtien += listChiTietGoiMon.get(i).getThanhTien();
            }
        }
        String strTongTien = FormatData.formatMoneyVietNam(tongtien);
        textViewTongTien.setText(strTongTien);
    }

    @Override
    protected void onPostExecute(Integer magoiMon) {
        super.onPostExecute(magoiMon);
    }
}
