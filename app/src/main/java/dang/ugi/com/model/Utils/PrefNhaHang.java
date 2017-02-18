package dang.ugi.com.model.Utils;

import android.content.Context;

import dang.ugi.com.model.Entities.CuaHang;

/**
 * Created by DANG on 10/2/2016.
 */

public class PrefNhaHang {
    public static final String TAG = PrefNhaHang.class.getSimpleName();
    public static void themCuaHangHienTai(CuaHang cuaHang, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "cuahang_hientai", 0);
        complexPreferences.putObject("cuahang_hientai", cuaHang);
        complexPreferences.commit();
    }

    public static CuaHang layCuaHangHienTai(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "cuahang_hientai", 0);
        CuaHang cuaHang = complexPreferences.getObject("cuahang_hientai", CuaHang.class);
        return cuaHang;
    }
    public static void xoaCuaHangHienTai( Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "cuahang_hientai", 0);
        complexPreferences.clearObject();
        complexPreferences.commit();
    }
}
