package dang.ugi.com.asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.adapter.GoiMonAdapter;
import dang.ugi.com.model.Entities.GoiMon;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.GoiMon.ImplGoiMonPresenter;

/**
 * Created by DANG on 11/24/2016.
 */

public class LoadGoiMonAsynTask extends AsyncTask<Integer, List<GoiMon>, List<GoiMon>> {
    private Context context;
    private View view;
    private List<GoiMon> listGoiMon;
    private ImplGoiMonPresenter implGoiMonPresenter;
    private int maCuaHang;
    RecyclerView recyclerView_list_goimon;
    public LoadGoiMonAsynTask(Context context,View view) {
        this.context = context;
        this.view = view;
        maCuaHang = PrefNhaHang.layCuaHangHienTai(context).getMaCuaHang();
        implGoiMonPresenter = new ImplGoiMonPresenter(context);
        recyclerView_list_goimon = (RecyclerView) view.findViewById(R.id.recycler_list_goimon);

    }

    @Override
    protected List<GoiMon> doInBackground(Integer... integers) {
        int tinhtrang = integers[0];
        if (tinhtrang ==2){
            listGoiMon = implGoiMonPresenter.listAllGoiMon(maCuaHang);
        }else{
            listGoiMon = implGoiMonPresenter.listAllGoiMonTheoTinhTrang(maCuaHang, tinhtrang);
        }

        publishProgress(listGoiMon);
        return listGoiMon;
    }

    @Override
    protected void onPostExecute(List<GoiMon> goiMons) {
        super.onPostExecute(goiMons);
    }

    @Override
    protected void onProgressUpdate(List<GoiMon>... values) {
        super.onProgressUpdate(values);
        listGoiMon = values[0];
        loadRecyclerView(listGoiMon);
    }
    public void loadRecyclerView(List<GoiMon> listGoiMon){
        GoiMonAdapter goiMonAdapter = new GoiMonAdapter(listGoiMon,context);
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_list_goimon.setHasFixedSize(true);
        recyclerView_list_goimon.setLayoutManager(linearLayoutManager);
        recyclerView_list_goimon.setAdapter(goiMonAdapter);
    }
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
