package dang.ugi.com.model.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import dang.ugi.com.view.NguoiDung.DangNhapActivity;

/**
 * Created by DANG on 9/7/2016.
 */
public class SessionDangNhap {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int MODE_PRIVATE = 0;
    public static final String PREF_NAME = "UgiPref";
    public static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_MANGUOIDUNG = "MaNguoiDung";
    public static final String KEY_MaCuaHang = "MaCuaHang";
    public SessionDangNhap(Context _context) {
        this._context = _context;
        pref = _context.getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        editor = pref.edit();
    }
    public  void createLogin(int MaNguoiDung,int maCuaHang){
        editor.putBoolean(IS_LOGIN,true);
        editor.putInt(KEY_MANGUOIDUNG,MaNguoiDung);
        editor.putInt(KEY_MaCuaHang,maCuaHang);
        editor.commit();
    }
    public void checkLogin(){
        if (!this.isLoggedIn()){
            Intent intent = new Intent(_context, DangNhapActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
        }
    }
    public void logout(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(_context,DangNhapActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
    public HashMap<String,String> layNguoiDung(){
        HashMap<String,String> nguoidung_cuaHang = new HashMap<>();
        nguoidung_cuaHang.put(KEY_MANGUOIDUNG,pref.getString(KEY_MANGUOIDUNG,""));
        nguoidung_cuaHang.put(KEY_MaCuaHang,pref.getString(KEY_MaCuaHang,""));
        return nguoidung_cuaHang;

    }
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN,false);
    }
}
