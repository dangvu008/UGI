package dang.ugi.com.presenter.NguoiDung;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

import java.util.List;

import dang.ugi.com.model.CuaHang.ImpCuaHangDatabaseHandler;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.NguoiDung;
import dang.ugi.com.model.NguoiDung.ImpNguoiDungDatabaseHandler;
import dang.ugi.com.model.Utils.CheckIsFirstRun;
import dang.ugi.com.model.Utils.PrefDangNhap;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.model.Utils.SessionDangNhap;
import dang.ugi.com.network.CuaHang.ConstantsCuaHang;
import dang.ugi.com.network.CuaHang.RequestInterfaceCuaHang;
import dang.ugi.com.network.CuaHang.ServerRequestCuaHang;
import dang.ugi.com.network.CuaHang.ServerResponseCuaHang;
import dang.ugi.com.network.NguoiDung.ConstantsNguoiDung;
import dang.ugi.com.network.NguoiDung.RequestInterfaceNguoiDung;
import dang.ugi.com.network.NguoiDung.ServerRequestNguoiDung;
import dang.ugi.com.network.NguoiDung.ServerResponseNguoiDung;
import dang.ugi.com.network.RetrofitHandler;
import dang.ugi.com.view.CuaHang.ThemCuaHangActivity;
import dang.ugi.com.view.NguoiDung.DangNhapActivity;
import dang.ugi.com.view.TrangChu.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by DANG on 9/8/2016.
 */
public class ImplNguoiDungPresenter implements INguoiDungPresenter {
    Context context;
    ImpNguoiDungDatabaseHandler dangNhapDatabaseHandler;
    ImpCuaHangDatabaseHandler impCuaHangDatabaseHandler;
    SessionDangNhap sessionDangNhap;
    CheckIsFirstRun checkIsFirstRun;
    NguoiDung nguoiDungdk;
    MdangNhap mdangNhap;
    Intent intent;
    int id;
    CuaHang cuaHang;
    AlertDialog alertDialog;
    public static final int REQUEST_CODE_DANGNHAP = 1;

    public ImplNguoiDungPresenter(Context context) {
        this.context = context;
        FacebookSdk.sdkInitialize(context);
        sessionDangNhap = new SessionDangNhap(context);
        checkIsFirstRun = new CheckIsFirstRun(context);
        dangNhapDatabaseHandler = new ImpNguoiDungDatabaseHandler(context);
        impCuaHangDatabaseHandler = new ImpCuaHangDatabaseHandler(context);
        StrictMode.setVmPolicy (new StrictMode.VmPolicy.Builder().detectAll().penaltyLog()
                .penaltyDeath().build());
      /*  System.gc();
        System.runFinalization();
        System.gc();*/

    }

    @Override
    public long themDuLieuNguoiDung(NguoiDung nguoiDung) {
        return dangNhapDatabaseHandler.themNguoiDung(nguoiDung);
    }

    @Override
    public boolean kiemtraTaiKhoanTonTai(String email) {
        return dangNhapDatabaseHandler.kiemTraEmailTonTai(email);
    }

    @Override
    public boolean kiemTraSoDienthoaiTonTai(String sodienthoai) {
        return dangNhapDatabaseHandler.kiemTraSoDienThoaiTonTai(sodienthoai);
    }

    @Override
    public boolean kiemTraTenTonTai(String ten) {
        return dangNhapDatabaseHandler.kiemTraTenTonTai(ten);
    }


    @Override
    public boolean xoaNguoiDung(int maNguoiDung) {
        return dangNhapDatabaseHandler.xoaNguoiDung(maNguoiDung);
    }

    @Override
    public int lastIdNguoiDung() {
        return dangNhapDatabaseHandler.lastIdNguoiDung();
    }

    @Override
    public boolean updateNguoiDung(NguoiDung nguoiDung) {
        return dangNhapDatabaseHandler.updateNguoiDung(nguoiDung);
    }

    @Override
    public AccessToken layTokenHienTai() {
        AccessToken accessToken = mdangNhap.layAccessTokenHientai();
        return accessToken;
    }

    @Override
    public NguoiDung dangNhap(String account, String matKhau) {
        return dangNhapDatabaseHandler.dangNhap(account, matKhau);
    }

    @Override
    public List<NguoiDung> listAllNguoiDung() {
        return dangNhapDatabaseHandler.listAllNguoiDung();
    }

    @Override
    public List<NguoiDung> listAllNguoiDung(int maCuaHang) {
        return dangNhapDatabaseHandler.listAllNguoiDung(maCuaHang);
    }

    @Override
    public List<NguoiDung> listUnsyncNguoiDung(int maCuaHang) {
        return dangNhapDatabaseHandler.listUnsyncNguoiDung(maCuaHang);
    }

    @Override
    public List<NguoiDung> timKiemNguoiDung(String keySearch, int maCuaHang) {
        return dangNhapDatabaseHandler.timKiemNguoiDung(keySearch,maCuaHang);
    }

    @Override
    public NguoiDung checkAllowLogin(String email) {
        return dangNhapDatabaseHandler.checkAllowLogin(email);
    }

    @Override
    public void destroyAccessToken() {
        mdangNhap.destroyAcessToken();
    }

    @Override
    public String setErrorEmail() {
        return null;
    }

    @Override
    public String setErrorMatKhau() {
        return null;
    }

    @Override
    public void chuyenManHinhChinh() {
        nguoiDungdk = PrefDangNhap.layNguoiDungHienTai(context);
        intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    public int dangkymoinguoidung(NguoiDung nguoiDungdk){
        nguoiDungdk.setMaQuyen(1);
        nguoiDungdk.setAllowLogin(1);
          return (int) themDuLieuNguoiDung(nguoiDungdk);

        //}
    }
    public void chuyenThemCuaHang(int lastIdCh){
        int lastId = lastIdCh+1;
        Intent intentdk = new Intent(context, ThemCuaHangActivity.class);
        intentdk.putExtra("maCuaHang",lastId);
        context.startActivity(intentdk);
    }
    private boolean ketqua;
    private NguoiDung nguoiDungCuoi;
    /*public NguoiDung lastIdNguoiDung(){
        Retrofit retrofit = RetrofitHandler.retrofit(ConstantsNguoiDung.BASE_URL);
        RequestInterfaceNguoiDung requestInterfaceNguoiDung = retrofit.create(RequestInterfaceNguoiDung.class);
        ServerRequestNguoiDung request= new ServerRequestNguoiDung();
        request.setOperation(ConstantsNguoiDung.LAST_ID);
        Call<ServerResponseNguoiDung> response = requestInterfaceNguoiDung.operation(request);
        response.enqueue(new Callback<ServerResponseNguoiDung>() {
            @Override
            public void onResponse(Call<ServerResponseNguoiDung> call, Response<ServerResponseNguoiDung> response) {
                nguoiDungCuoi = response.body().getNguoiDung();
            }

            @Override
            public void onFailure(Call<ServerResponseNguoiDung> call, Throwable t) {
                Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return nguoiDungCuoi;
    }*/
    private CuaHang cuaHangCuoi;
    public CuaHang lastIdCuaHang(){
        Retrofit retrofit = RetrofitHandler.retrofit(ConstantsCuaHang.BASE_URL);
        RequestInterfaceCuaHang requestInterface = retrofit.create(RequestInterfaceCuaHang.class);
        ServerRequestCuaHang request= new ServerRequestCuaHang();
        request.setOperation(ConstantsCuaHang.LAST_ID);
        Call<ServerResponseCuaHang> response = requestInterface.operation(request);
        response.enqueue(new Callback<ServerResponseCuaHang>() {
            @Override
            public void onResponse(Call<ServerResponseCuaHang> call, Response<ServerResponseCuaHang> response) {
                cuaHangCuoi = response.body().getCuaHang();
            }

            @Override
            public void onFailure(Call<ServerResponseCuaHang> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return cuaHangCuoi;
    }
    public CuaHang checkCuahangNguoiDung(NguoiDung nguoiDung){
        Retrofit retrofit = RetrofitHandler.retrofit(ConstantsNguoiDung.BASE_URL);
        RequestInterfaceCuaHang requestInterface = retrofit.create(RequestInterfaceCuaHang.class);
        ServerRequestCuaHang request= new ServerRequestCuaHang();
        request.setOperation(ConstantsCuaHang.CHECK_NGUOIDUNG_CUAHANG);
        request.setNguoiDung(nguoiDung);
        Call<ServerResponseCuaHang> response = requestInterface.operation(request);
        response.enqueue(new Callback<ServerResponseCuaHang>() {
            @Override
            public void onResponse(Call<ServerResponseCuaHang> call, Response<ServerResponseCuaHang> response) {
                cuaHang = response.body().getCuaHang();
            }

            @Override
            public void onFailure(Call<ServerResponseCuaHang> call, Throwable t) {

            }
        });
        return cuaHang;
    }

    public boolean themNguoiDungtoServer(NguoiDung nguoiDung) {
        Retrofit retrofit = RetrofitHandler.retrofit(ConstantsNguoiDung.BASE_URL);
        RequestInterfaceNguoiDung requestInterfaceNguoiDung = retrofit.create(RequestInterfaceNguoiDung.class);
         ServerRequestNguoiDung request = new ServerRequestNguoiDung();
        request.setNguoiDung(nguoiDung);
        request.setOperation(ConstantsNguoiDung.REGISTER_SOCCIAL);
        Call<ServerResponseNguoiDung> response = requestInterfaceNguoiDung.operation(request);
        response.enqueue(new Callback<ServerResponseNguoiDung>() {
            @Override
            public void onResponse(Call<ServerResponseNguoiDung> call, Response<ServerResponseNguoiDung> response) {
                int statuscode = response.code();
                if (response.isSuccessful()){
                    ketqua = true;
                    Toast.makeText(context, "successful!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponseNguoiDung> call, Throwable t) {
                ketqua = false;
                Log.e("them loi tai :",t.getMessage());
                Log.e("loi o dayne:",t.getLocalizedMessage());
            }
        });
        if (ketqua==false)
            PrefDangNhap.xoaNguoiDungHienTai(context);
        return ketqua;

    }
    public void dangxuat() {
        PrefDangNhap.xoaNguoiDungHienTai(context);
        PrefNhaHang.xoaCuaHangHienTai(context);
        chuyenManHinhDanhNhap((AppCompatActivity) context);

    }

    public void chuyenManHinhDanhNhap(final AppCompatActivity activity) {
        Intent intent = new Intent(context, DangNhapActivity.class);
        activity.startActivityForResult(intent, REQUEST_CODE_DANGNHAP);
    }

}


