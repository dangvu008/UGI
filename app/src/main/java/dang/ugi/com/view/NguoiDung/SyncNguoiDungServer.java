package dang.ugi.com.view.NguoiDung;

import android.content.Context;

import java.util.List;

import dang.ugi.com.model.Entities.NguoiDung;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.network.NguoiDung.ConstantsNguoiDung;
import dang.ugi.com.network.NguoiDung.RequestInterfaceNguoiDung;
import dang.ugi.com.network.RetrofitHandler;
import dang.ugi.com.network.NguoiDung.ServerRequestNguoiDung;
import dang.ugi.com.presenter.NguoiDung.ImplNguoiDungPresenter;
import retrofit2.Retrofit;

/**
 * Created by DANG on 1/5/2017.
 */

public class SyncNguoiDungServer {
    RetrofitHandler retrofitHandler;
    ImplNguoiDungPresenter implNguoiDungPresenter;
    Context context;
    int maCuaHang;
    public SyncNguoiDungServer(Context context) {
        this.context = context;
        implNguoiDungPresenter = new ImplNguoiDungPresenter(context);
        maCuaHang = PrefNhaHang.layCuaHangHienTai(context).getMaCuaHang();
    }

    public void syncNguoiDungToServer(){
        List<NguoiDung> listNguoiDung = implNguoiDungPresenter.listUnsyncNguoiDung(maCuaHang);
      /*  JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();*/
      /*  Type type = new  TypeToken<List<NguoiDung>>(){}.getType();
        Gson gson = new Gson();
        String jsonNguoiDung = gson.toJson(listNguoiDung);*/
        Retrofit retrofit = RetrofitHandler.retrofit(ConstantsNguoiDung.BASE_URL);
        RequestInterfaceNguoiDung requestInterfaceNguoiDung = retrofit.create(RequestInterfaceNguoiDung.class);
        ServerRequestNguoiDung serverRequestNguoiDung = new ServerRequestNguoiDung();
        serverRequestNguoiDung.setOperation(ConstantsNguoiDung.SYNC_NGUOIDUNG_OPERATION);
        serverRequestNguoiDung.setListNguoiDung(listNguoiDung);




    }
}
