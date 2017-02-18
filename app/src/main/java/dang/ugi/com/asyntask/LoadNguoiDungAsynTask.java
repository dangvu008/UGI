package dang.ugi.com.asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.adapter.NguoiDungAdapter;
import dang.ugi.com.model.Entities.NguoiDung;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.NguoiDung.ImplNguoiDungPresenter;

/**
 * Created by DANG on 1/5/2017.
 */

public class LoadNguoiDungAsynTask extends AsyncTask<String,List<NguoiDung>,List<NguoiDung>> {
    private int maCuaHang ;
    private Context context;
    private ImplNguoiDungPresenter implNguoiDungPresenter;
    private RecyclerView recyclerView;
    private  View view;
    public LoadNguoiDungAsynTask(Context context, View view) {
        this.context = context;
        maCuaHang  = PrefNhaHang.layCuaHangHienTai(context).getMaCuaHang();
        implNguoiDungPresenter = new ImplNguoiDungPresenter(context);
        this.view = view;
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_NguoiDung);
    }

    @Override
    protected List<NguoiDung> doInBackground(String... strings) {
        List<NguoiDung> list;
        String key =strings[0];
        if (key==""){
            list = implNguoiDungPresenter.listAllNguoiDung(maCuaHang);
        }else{
            list = implNguoiDungPresenter.timKiemNguoiDung(key,maCuaHang);
        }
        publishProgress(list);
        return list;
    }

    @Override
    protected void onProgressUpdate(List<NguoiDung>... values) {
        super.onProgressUpdate(values);
        List<NguoiDung> list = values[0];
        LoadToRecyclerView(list);
    }

    private void LoadToRecyclerView(List<NguoiDung> list) {
        NguoiDungAdapter adapter = new NguoiDungAdapter(context,list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onPostExecute(List<NguoiDung> nguoiDungs) {
        super.onPostExecute(nguoiDungs);
    }
}
