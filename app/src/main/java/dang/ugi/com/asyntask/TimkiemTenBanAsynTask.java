package dang.ugi.com.asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.adapter.BanAnSearchAdapter;
import dang.ugi.com.model.Entities.BanAn;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.BanAn.ImplPresenterBanAn;


/**
 * Created by DANG on 11/18/2016.
 */

public class TimkiemTenBanAsynTask extends AsyncTask<String, List<BanAn>, List<BanAn>> {

    private Context context;
    BanAnSearchAdapter banAnSearchAdapter;
    List<BanAn> listBanAn;
    ImplPresenterBanAn implPresenterBanAn;
    AutoCompleteTextView autoCompleteTextView;
    int maCuahang,chucNang;
    //chuc nang  = 0 : goi mon
    //chuc nang = 1:tim kiem ten ban
    //chuc nang = 2 :tim kiem ten chuyen ban
    public TimkiemTenBanAsynTask(Context context,int chucNang) {
        this.context = context;
          CuaHang cuahang = PrefNhaHang.layCuaHangHienTai(context);
        if (cuahang!=null)
            maCuahang = cuahang.getMaCuaHang();
        if (chucNang==0)
            autoCompleteTextView = (AutoCompleteTextView) ((AppCompatActivity)context).findViewById(R.id.autocomplete_danggoimon_tenban);
        else if (chucNang==2)
            autoCompleteTextView = (AutoCompleteTextView) ((AppCompatActivity)context).findViewById(R.id.autocomplete_chuyenban_tenban);
        implPresenterBanAn = new ImplPresenterBanAn(context);

    }

    @Override
    protected List<BanAn> doInBackground(String... strings) {
        String keySearch = strings[0];
            if (keySearch.length()==0){
                listBanAn = implPresenterBanAn.layToanBoBanAn(maCuahang);
            }else{
                listBanAn = implPresenterBanAn.timKiemBanAnBangTen(keySearch, maCuahang);
            }
        publishProgress(listBanAn);
        return listBanAn;
    }

    @Override
    protected void onProgressUpdate(List<BanAn>... values) {
        super.onProgressUpdate(values);
        List<BanAn> list = values[0];
        hienthiTenBan(list);
    }

    @Override
    protected void onPostExecute(List<BanAn> list) {
        super.onPostExecute(list);
    }

    private void hienthiTenBan(List<BanAn> list) {
        banAnSearchAdapter = new BanAnSearchAdapter(context, list);
        autoCompleteTextView.setAdapter(banAnSearchAdapter);
        autoCompleteTextView.showDropDown();
        banAnSearchAdapter.setNotifyOnChange(true);
    }

}