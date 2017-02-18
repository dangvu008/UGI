package dang.ugi.com.presenter.LoaiMonAn;

import android.content.Context;

import java.util.List;

import dang.ugi.com.model.Entities.LoaiMon;
import dang.ugi.com.model.LoaiMonAn.ImpLoaiMonAnDatabaseHandler;

/**
 * Created by DANG on 9/26/2016.
 */

public class ImplLoaiMonAnPresenter implements ILoaiMonAnPresenter {
    Context context;
    ImpLoaiMonAnDatabaseHandler databaseHandler;

    public ImplLoaiMonAnPresenter(Context context) {
        this.context = context;
        databaseHandler = new ImpLoaiMonAnDatabaseHandler(context);
    }

    @Override
    public List<LoaiMon> listLoaiMon() {
        return databaseHandler.listLoaiMon();
    }

    @Override
    public List<String> listTenLoaiMon() {
        return databaseHandler.listTenLoaiMon();
    }

    @Override
    public List<LoaiMon> listLoaiMonTheoCuaHang(int maCuaHang) {
        return databaseHandler.listLoaiMonTheoCuaHang(maCuaHang);
    }

    @Override
    public LoaiMon layLoaiMonById(int maLoaiMon) {
        return databaseHandler.layLoaiMonById(maLoaiMon);
    }

    @Override
    public long themLoaiMon(LoaiMon loaiMon) {
        return databaseHandler.themLoaiMon(loaiMon);
    }

    @Override
    public long CapNhatLoaiMon(LoaiMon loaiMon) {
        return databaseHandler.themLoaiMon(loaiMon);
    }

    @Override
    public long xoaLoaiMon(int maLoaiMon) {
        return databaseHandler.xoaLoaiMon(maLoaiMon);
    }

    @Override
    public boolean kiemtraLoaiMonTonTai(String tenLoaiMon) {
        return databaseHandler.kiemTraLoaiMonTonTai(tenLoaiMon);
    }
}
