package dang.ugi.com.view.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.facebook.appevents.AppEventsLogger;
import com.facebook.stetho.Stetho;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.NguoiDung;
import dang.ugi.com.model.Utils.PrefDangNhap;
import dang.ugi.com.presenter.NguoiDung.ImplNguoiDungPresenter;
import dang.ugi.com.view.NguoiDung.FragmentDangNhap;

public class ManHinhChaoActivity extends AppCompatActivity {
    ImplNguoiDungPresenter implPresenterDangNhap;
    NguoiDung nguoiDungCheck;
    CoordinatorLayout layout;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppEventsLogger.activateApp(this);
        Stetho.initializeWithDefaults(this);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog()
                .penaltyDeath().build());
        setContentView(R.layout.activity_man_hinh_chao);
       layout = (CoordinatorLayout) findViewById(R.id.layout_manhinhchao);
        implPresenterDangNhap = new ImplNguoiDungPresenter(this);

         nguoiDungCheck = PrefDangNhap.layNguoiDungHienTai(getApplicationContext());
                if (nguoiDungCheck!=null){
                    implPresenterDangNhap.chuyenManHinhChinh();
                }else{
                    implPresenterDangNhap.chuyenManHinhDanhNhap(ManHinhChaoActivity.this);
                }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == FragmentDangNhap.RESULT_CODE_DANGNHAP_FB_THANHCONG && requestCode == ImplNguoiDungPresenter.REQUEST_CODE_DANGNHAP){
            Bundle bundleDKTraVe = data.getBundleExtra("bNguoiDung");
            NguoiDung  nguoiDung = (NguoiDung) bundleDKTraVe.getSerializable("NGUOIDUNG");
            boolean kiemTra = implPresenterDangNhap.kiemtraTaiKhoanTonTai(nguoiDung.getEmail());
            if (kiemTra){
                implPresenterDangNhap.chuyenManHinhChinh();
            }else{
                implPresenterDangNhap.dangkimoi(nguoiDung);

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (nguoiDungCheck!=null){
            implPresenterDangNhap.chuyenManHinhChinh();
        }else{
            implPresenterDangNhap.chuyenManHinhDanhNhap(ManHinhChaoActivity.this);
        }

    }


}



