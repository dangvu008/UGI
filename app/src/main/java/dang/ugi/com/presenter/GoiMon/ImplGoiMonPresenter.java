package dang.ugi.com.presenter.GoiMon;

import android.content.Context;

import java.util.List;

import dang.ugi.com.model.Entities.GoiMon;
import dang.ugi.com.model.GoiMon.ImplGoiMonDatabaseHandler;

/**
 * Created by DANG on 11/20/2016.
 */

public class ImplGoiMonPresenter implements IGoiMonPresenter {
    Context context;
    ImplGoiMonDatabaseHandler databaseHandler;

    public ImplGoiMonPresenter(Context context) {
        this.context = context;
        databaseHandler = new ImplGoiMonDatabaseHandler(context);
    }

    @Override
    public long themGoiMon(GoiMon goiMon) {
        return databaseHandler.themGoiMon(goiMon);
    }

    @Override
    public long themTamGoiMon(GoiMon goiMon) {
        return databaseHandler.themTamGoiMon(goiMon);
    }

    @Override
    public long capNhatGoiMon(GoiMon goiMon) {
        return databaseHandler.capNhatGoiMon(goiMon);
    }

    @Override
    public long xoaGoiMon(int maGoiMon) {
        return databaseHandler.xoaGoiMon(maGoiMon);
    }

    @Override
    public long xoaLichSuGoiMonTam() {
        return databaseHandler.xoaLichSuGoiMonTam();
    }

    @Override
    public List<GoiMon> listAllGoiMon(int maCuaHang) {
        return databaseHandler.listAllGoiMon(maCuaHang);
    }

    @Override
    public List<GoiMon> listAllGoiMonTheoTinhTrang(int maCuaHang, int tinhTrang) {
        return databaseHandler.listAllGoiMonTheoTinhTrang(maCuaHang,tinhTrang);
    }

    @Override
    public GoiMon layGoiMonTheoMaGoiMon(int maGoiMon) {
        return databaseHandler.layGoiMonTheoMaGoiMon(maGoiMon);
    }

    @Override
    public GoiMon layGoiMonDangGoi(int maBan) {
        return databaseHandler.layGoiMonDangGoi(maBan);
    }


}
