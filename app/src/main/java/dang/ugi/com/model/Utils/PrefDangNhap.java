package dang.ugi.com.model.Utils;

import android.content.Context;

import dang.ugi.com.model.Entities.NguoiDung;

/**
 * Created by DANG on 9/14/2016.
 */
public class PrefDangNhap {

    public static final String TAG = PrefDangNhap.class.getSimpleName();
    public static void themNguoiDungHienTai(NguoiDung nguoiDung, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "nguoidung_hientai",0);
        complexPreferences.putObject("nguoidung_hientai", nguoiDung);
        complexPreferences.commit();
    }

    public static NguoiDung layNguoiDungHienTai(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "nguoidung_hientai",0);
        NguoiDung mNguoiDung = complexPreferences.getObject("nguoidung_hientai", NguoiDung.class);
        return mNguoiDung;
    }
    public static void xoaNguoiDungHienTai( Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "nguoidung_hientai", 0);
        complexPreferences.clearObject();
        complexPreferences.commit();
    }


}
