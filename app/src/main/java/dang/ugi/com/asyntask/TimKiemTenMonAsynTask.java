package dang.ugi.com.asyntask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.AutoCompleteTextView;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.adapter.MonAnSearchAdapter;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.MonAn;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.MonAn.ImpPresenterMonAn;

/**
 * Created by DANG on 11/18/2016.
 */

public class TimKiemTenMonAsynTask extends AsyncTask<String,List<MonAn>,List<MonAn>> {
    Context context;
    List<MonAn> listMonAn;
    MonAnSearchAdapter monAnSearchAdapter;
    AutoCompleteTextView autoCompleteTextView;
    ImpPresenterMonAn impPresenterMonAn;
    private  int maCuaHang;
    public TimKiemTenMonAsynTask(Context context) {
        this.context = context;
        impPresenterMonAn = new ImpPresenterMonAn(context);
        autoCompleteTextView = (AutoCompleteTextView) ((Activity)context).findViewById(R.id.autoCompleteTextView_dialog_goimon_tenmon);
       CuaHang cuahang = PrefNhaHang.layCuaHangHienTai(context);
        if(cuahang!=null)
             maCuaHang = cuahang.getMaCuaHang();
    }

    @Override
    protected List<MonAn> doInBackground(String... strings) {
        String keySearch = strings[0];
        if (keySearch==""){
            listMonAn = impPresenterMonAn.listAllMonAnTheoCuaHang(maCuaHang);

        }else{
            listMonAn = impPresenterMonAn.listTimKiemMonAnTrongThucDon(keySearch,maCuaHang);
        }

        publishProgress(listMonAn);
        return listMonAn;
    }

    @Override
    protected void onProgressUpdate(List<MonAn>... values) {
        super.onProgressUpdate(values);
        List<MonAn> list = values[0];
        monAnSearchAdapter = new MonAnSearchAdapter(context,0,list);
        autoCompleteTextView.setAdapter(monAnSearchAdapter);
        autoCompleteTextView.showDropDown();
        monAnSearchAdapter.setNotifyOnChange(true);
    }

    @Override
    protected void onPostExecute(List<MonAn> list) {
        super.onPostExecute(list);
    }
}
