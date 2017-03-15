package dang.ugi.com.asyntask;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.adapter.BanAnAdapter;
import dang.ugi.com.model.Entities.BanAn;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Utils.GridSpacingItemDecoration;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.BanAn.ImplPresenterBanAn;

/**
 * Created by DANG on 11/27/2016.
 */

public class LoadBanAsynTask extends AsyncTask<String,List<BanAn>,List<BanAn>>{
    private Context context;
    private List<BanAn> listBanAn;
    private RecyclerView recyclerView;
    private View view;
    private int maCuaHang;
    private BanAnAdapter banAnAdapter;
    private ImplPresenterBanAn implPresenterBanAn;
    public LoadBanAsynTask(Context context,View view) {
        this.context = context;
        this.view = view;
        CuaHang cuaHangHT = PrefNhaHang.layCuaHangHienTai(context);
        if (cuaHangHT!=null)
            maCuaHang = cuaHangHT.getMaCuaHang();
        implPresenterBanAn = new ImplPresenterBanAn(context);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyerView_banAn_main);
    }

    @Override
    protected List<BanAn> doInBackground(String... strings) {
        String keySearch = strings[0];
        if (keySearch==""){
            listBanAn = implPresenterBanAn.layToanBoBanAn(maCuaHang);
        }else {
            listBanAn=  implPresenterBanAn.timKiemBanAnBangTen(keySearch,maCuaHang);
        }
        publishProgress(listBanAn);
        return listBanAn;
    }
    public void loadDataToRecyerView(List<BanAn> list) {
        RecyclerView.LayoutManager layoutManager;
        banAnAdapter = new BanAnAdapter(context,list);
        if (((Activity)context).getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
             layoutManager = new GridLayoutManager(context, 2);
        }else {
             layoutManager = new GridLayoutManager(context, 5);
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPX(5), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(banAnAdapter);
    }
    public int dpToPX(int dp) {
        Resources resources = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()));

    }
    @Override
    protected void onProgressUpdate(List<BanAn>... values) {
        super.onProgressUpdate(values);
        List<BanAn> list = values[0];
        loadDataToRecyerView(list);
    }

    @Override
    protected void onPostExecute(List<BanAn> list) {
        super.onPostExecute(list);
    }
}
