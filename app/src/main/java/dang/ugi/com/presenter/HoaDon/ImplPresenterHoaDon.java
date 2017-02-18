package dang.ugi.com.presenter.HoaDon;

import android.content.Context;

import java.util.List;

import dang.ugi.com.model.Entities.HoaDon;
import dang.ugi.com.model.HoaDon.ImplHoaDonDatabaseHandler;

/**
 * Created by DANG on 12/12/2016.
 */

public class ImplPresenterHoaDon implements IPresenterHoaDon {
    Context context;
    ImplHoaDonDatabaseHandler implHoaDonDatabaseHandler;

    public ImplPresenterHoaDon(Context context) {
        this.context = context;
        implHoaDonDatabaseHandler = new ImplHoaDonDatabaseHandler(context);
    }

    @Override
    public long themHoaDon(HoaDon hoaDon) {
        return implHoaDonDatabaseHandler.themHoaDon(hoaDon);
    }

    @Override
    public List<HoaDon> listAllHoaDon() {
        return implHoaDonDatabaseHandler.listAllHoaDon();
    }

    @Override
    public float doanhThuNgay(String ngay, int maCuaHang) {
        return implHoaDonDatabaseHandler.doanhThuNgay(ngay,maCuaHang);
    }

    @Override
    public float doanhThuThang(String thang, int maCuaHang) {
        return implHoaDonDatabaseHandler.doanhThuThang(thang,maCuaHang);
    }

    @Override
    public float doanhThuNam(String nam, int maCuaHang) {
        return implHoaDonDatabaseHandler.doanhThuNam(nam,maCuaHang);
    }

    @Override
    public long xoaHoaDon(int maHoaDon) {
        return implHoaDonDatabaseHandler.xoaHoaDon(maHoaDon);
    }

    @Override
    public float doanhThuTheoBan(int maBan) {
        return implHoaDonDatabaseHandler.doanhThuTheoBan(maBan);
    }
}
