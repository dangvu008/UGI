package dang.ugi.com.presenter.MonAn;

import android.content.Context;

import java.util.List;

import dang.ugi.com.model.Entities.Gia;
import dang.ugi.com.model.Entities.MonAn;
import dang.ugi.com.model.MonAn.ImpMonAnDatabaseHandler;

/**
 * Created by DANG on 9/27/2016.
 */

public class ImpPresenterMonAn implements IpresenterMonAn {
    Context context;
    ImpMonAnDatabaseHandler databaseHandler;

    public ImpPresenterMonAn(Context context) {
        this.context = context;
        databaseHandler = new ImpMonAnDatabaseHandler(context);
    }

    @Override
    public long themMonAn(MonAn monAn) {
        return databaseHandler.themMonAn(monAn);
    }

    @Override
    public long capNhatMonAn(MonAn monAn) {
        return databaseHandler.capNhatMonAn(monAn);
    }

    @Override
    public List<MonAn> listAllMonAnTheoMaLoai(int maLoai) {
        return databaseHandler.listAllMonAnTheoMaLoai(maLoai);
    }

    @Override
    public List<MonAn> listAllMonAnTheoCuaHang(int maCuaHang) {
        return databaseHandler.listAllMonAnTheoCuaHang(maCuaHang);
    }

    @Override
    public List<MonAn> listAllMonAnTheoMaLoaiChiTiet(int maLoai) {
        return databaseHandler.listAllMonAnTheoMaLoai(maLoai);
    }


    @Override
    public List<MonAn> listAllMonAn() {
        return databaseHandler.listAllMonAn();
    }

    @Override
    public List<MonAn> listTimKiemMonAn(String tenMonAn) {
        return databaseHandler.listTimKiemMonAn(tenMonAn);
    }

    @Override
    public List<MonAn> listTimKiemMonAnTrongThucDon(String tenMonAn, int maCuaHang) {
        return databaseHandler.listTimKiemMonAnTrongThucDon(tenMonAn,maCuaHang);
    }

    @Override
    public MonAn layMonAnbyId(int id) {
        return databaseHandler.layMonAnbyId(id);
    }

    @Override
    public long xoaMonAn(int id) {
        return databaseHandler.xoaMonAn(id);
    }

    @Override
    public long xoaBangGia(int maMon, int maCuaHang) {
        return databaseHandler.xoaBangGia(maMon,maCuaHang);
    }

    @Override
    public boolean kiemTraTenMonTonTai(String tenMon) {
        return databaseHandler.kiemTraTenMonTonTai(tenMon);
    }

    @Override
    public boolean kiemtraMonDacoTrongThucDon(int maMon, int maCuaHang) {
        return databaseHandler.kiemtraMonDacoTrongThucDon(maMon,maCuaHang);
    }

    @Override
    public boolean kiemTraTenMonTonTaiTrongThucDon(String tenMon, int maCuaHang) {
        return databaseHandler.kiemTraTenMonTonTaiTrongThucDon(tenMon,maCuaHang);
    }

    @Override
    public long themBangGia(Gia gia) {
        return databaseHandler.themBangGia(gia);
    }

    @Override
    public float layGiaMonTheoMaMon(int maMon) {
        return databaseHandler.layGiaMonTheoMaMon(maMon);
    }

    @Override
    public int layMaCuaHangByMaMon(int maMon) {
        return databaseHandler.layMaCuaHangByMaMon(maMon);
    }
}
