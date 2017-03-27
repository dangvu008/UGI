package dang.ugi.com.asyntask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.adapter.ChiTietGoiMonAdapter;
import dang.ugi.com.model.Entities.ChiTietGoiMon;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.GoiMon;
import dang.ugi.com.model.Utils.FormatData;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.ChiTietGoiMon.ImplChiTietGoiMonPresenter;
import dang.ugi.com.presenter.GoiMon.ImplGoiMonPresenter;

/**
 * Created by DANG on 11/21/2016.
 */

public class LoadFormGoiMonAsynTask extends AsyncTask<Integer, List<ChiTietGoiMon>, List<ChiTietGoiMon>> {

    private List<ChiTietGoiMon> listChiTietGoiMon;
    private Context context;
    private ImplChiTietGoiMonPresenter implChiTietGoiMonPresenter;
    private ImplGoiMonPresenter implGoiMonPresenter;
    private int maCuaHang;
    private GoiMon goiMon;
    private AutoCompleteTextView autoCompleteTextView_goimon_asyn_tenban;
    private EditText editTextTienThem,editTextGhiChu;
    private TextView textViewTongTien,textView_thongbaorong;
    private RecyclerView recyclerView_ChiTietGoiMon;
    public LoadFormGoiMonAsynTask(Context context) {
        this.context = context;
        implChiTietGoiMonPresenter = new ImplChiTietGoiMonPresenter(context);
        implGoiMonPresenter = new ImplGoiMonPresenter(context);
        CuaHang cuaHang = PrefNhaHang.layCuaHangHienTai(context);
        if (cuaHang!=null)
            maCuaHang = cuaHang.getMaCuaHang();
        loadControls(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    private void loadControls(Context context) {
        autoCompleteTextView_goimon_asyn_tenban = (AutoCompleteTextView) ((Activity)context).findViewById(R.id.autocomplete_danggoimon_tenban);
        editTextGhiChu  = (EditText) ((Activity)context).findViewById(R.id.editText_danggoimon_ghichu);
        editTextTienThem = (EditText) ((Activity)context).findViewById(R.id.editText_danggoimon_tienThem);
        textViewTongTien = (TextView) ((Activity)context).findViewById(R.id.textview_DangGoiMon_TongTien);
        textView_thongbaorong = (TextView) ((Activity)context).findViewById(R.id.textview_DangGoiMon_thongbaorong);
        recyclerView_ChiTietGoiMon = (RecyclerView) ((Activity)context).findViewById(R.id.recycler_danggoimon_listMonDangGoi);
    }


    /**
     * param 0 load recyclerview
     * */
    @Override
    protected List<ChiTietGoiMon> doInBackground(Integer... integers) {
        int maGoiMon = integers[0];
        listChiTietGoiMon =implChiTietGoiMonPresenter.listChiTietGoiMon(maGoiMon);
        publishProgress(listChiTietGoiMon);
        return listChiTietGoiMon;
    }

    private void loadRecyclerView(List<ChiTietGoiMon> chiTietGoiMonList) {
       if (chiTietGoiMonList.size()==0){
           recyclerView_ChiTietGoiMon.setVisibility(View.GONE);
           textView_thongbaorong.setVisibility(View.VISIBLE);
       }else {
           textView_thongbaorong.setVisibility(View.GONE);
           recyclerView_ChiTietGoiMon.setVisibility(View.VISIBLE);
           ChiTietGoiMonAdapter chiTietGoiMonAdapter = new ChiTietGoiMonAdapter(context,chiTietGoiMonList);
           LinearLayoutManager layoutManager = new LinearLayoutManager(context);
           layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
           recyclerView_ChiTietGoiMon.setLayoutManager(layoutManager);
           recyclerView_ChiTietGoiMon.setAdapter(chiTietGoiMonAdapter);
           chiTietGoiMonAdapter.notifyDataSetChanged();
       }
    }

    @Override
    protected void onProgressUpdate(List<ChiTietGoiMon>... chitietgoimonList) {
        super.onProgressUpdate(chitietgoimonList);
        listChiTietGoiMon = chitietgoimonList[0];
        loadRecyclerView(listChiTietGoiMon);
        loadToTextview(listChiTietGoiMon);

    }

   private void loadToTextview(List<ChiTietGoiMon> listChiTietGoiMon) {
        float tongtien = 0;
        for(int i=0;i<listChiTietGoiMon.size();i++){
            tongtien += listChiTietGoiMon.get(i).getThanhTien();
        }
        String strTongTien = FormatData.formatMoneyVietNam(tongtien);
        textViewTongTien.setText(strTongTien);
    }

    @Override
    protected void onPostExecute(List<ChiTietGoiMon> chiTietGoiMons) {
        super.onPostExecute(chiTietGoiMons);
    }
}
