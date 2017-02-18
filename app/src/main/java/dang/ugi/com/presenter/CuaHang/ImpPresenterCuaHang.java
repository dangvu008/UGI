package dang.ugi.com.presenter.CuaHang;

import android.content.Context;

import java.util.List;

import dang.ugi.com.model.CuaHang.ImpCuaHangDatabaseHandler;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.LoaiCuaHang;

/**
 * Created by DANG on 9/28/2016.
 */

public class ImpPresenterCuaHang implements IPresenterCuaHang {
    Context context;
    ImpCuaHangDatabaseHandler databaseHandler;

    public ImpPresenterCuaHang(Context context) {
        this.context = context;
        databaseHandler = new ImpCuaHangDatabaseHandler(context);
    }

    @Override
    public List<CuaHang> layToanBoCuaHang(int maNguoiDung) {
        return databaseHandler.layToanBoCuaHang(maNguoiDung);
    }

    @Override
    public List<CuaHang> timKiemCuahang(String tenCuaHang, int maNguoiDung) {
        return databaseHandler.timKiemCuahang(tenCuaHang,maNguoiDung);
    }

    @Override
    public CuaHang layCuahangById(int maCuaHang) {
        return databaseHandler.layCuahangById(maCuaHang);
    }

    @Override
    public boolean kiemTratenCuahang(String tenCuaHang) {
        return databaseHandler.kiemTratenCuahang(tenCuaHang);
    }

    @Override
    public long themCuaHang(CuaHang cuaHang) {
        return databaseHandler.themCuaHang(cuaHang);
    }

    @Override
    public long themNguoiDungCuaHang(int maNguoiDung, int maCuaHang) {
        return databaseHandler.themCuaHangNguoiDung(maCuaHang,maNguoiDung);
    }

    @Override
    public long suaCuaHang(CuaHang cuaHang) {
        return databaseHandler.suaCuaHang(cuaHang);
    }

    @Override
    public long xoaCuaHang(int maCuaHang) {
        return databaseHandler.xoaCuaHang(maCuaHang);
    }

    @Override
    public long themCuaHangNguoiDung(int maCuaHang, int maNguoiDung) {
        return databaseHandler.themCuaHangNguoiDung(maCuaHang,maNguoiDung);
    }

    @Override
    public int layMaCuaHangByMaNguoiDung(int maNguoiDung) {
        return databaseHandler.layMaCuaHangByMaNguoiDung(maNguoiDung);
    }

    @Override
    public int themLoaiCuaHang(String tenLoaiCuaHang) {
        return databaseHandler.themLoaiCuaHang(tenLoaiCuaHang);
    }

    @Override
    public List<LoaiCuaHang> layLoaiCuaHang() {
        return databaseHandler.layLoaiCuaHang();
    }
}
