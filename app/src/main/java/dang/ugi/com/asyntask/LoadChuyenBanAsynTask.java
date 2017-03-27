package dang.ugi.com.asyntask;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.AutoCompleteTextView;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.adapter.BanAnSearchAdapter;
import dang.ugi.com.model.Entities.BanAn;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.BanAn.ImplPresenterBanAn;

/**
 * Created by DANG on 11/18/2016.
 */

public class LoadChuyenBanAsynTask extends AsyncTask<String, List<BanAn>, List<BanAn>> {

    private Activity context;
    BanAnSearchAdapter banAnSearchAdapter;
    List<BanAn> listBanAn;
    ImplPresenterBanAn implPresenterBanAn;
    AutoCompleteTextView autoCompleteTextView;
    int maCuahang,maBanHienTai;
    public LoadChuyenBanAsynTask(Activity context,int maBanHienTai) {
        this.context = context;
        this.maBanHienTai = maBanHienTai;
        maCuahang = PrefNhaHang.layCuaHangHienTai(context).getMaCuaHang();
        implPresenterBanAn = new ImplPresenterBanAn(context);
        autoCompleteTextView = (AutoCompleteTextView) ((Activity)context).findViewById(R.id.autocomplete_danggoimon_tenban);
    }

    @Override
    protected List<BanAn> doInBackground(String... strings) {
        String keySearch = strings[0];
            if (keySearch.length()==0){
                listBanAn = implPresenterBanAn.listAllChuyenBan(maCuahang, maBanHienTai);
            }else{
                listBanAn = implPresenterBanAn.timKiemBanAnBangTen(keySearch, maCuahang,maBanHienTai);
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