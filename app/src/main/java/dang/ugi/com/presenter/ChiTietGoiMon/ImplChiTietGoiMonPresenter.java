package dang.ugi.com.presenter.ChiTietGoiMon;

import android.content.Context;

import java.util.List;

import dang.ugi.com.model.ChiTietGoiMon.ImplChiTietGoiMonDatabaseHandler;
import dang.ugi.com.model.Entities.ChiTietGoiMon;

/**
 * Created by DANG on 11/20/2016.
 */

public class ImplChiTietGoiMonPresenter implements IChiTietGoiMonPresenter {
    Context context;
    ImplChiTietGoiMonDatabaseHandler databaseHandler;

    public ImplChiTietGoiMonPresenter(Context context) {
        this.context = context;
        databaseHandler = new ImplChiTietGoiMonDatabaseHandler(context);
    }

    @Override
    public long themChiTietGoiMon(ChiTietGoiMon chiTietGoiMon) {
        return databaseHandler.themChiTietGoiMon(chiTietGoiMon);
    }

    @Override
    public long capnhatChiTietGoiMon(ChiTietGoiMon chiTietGoiMon) {
        return databaseHandler.capnhatChiTietGoiMon(chiTietGoiMon);
    }

    @Override
    public long capnhatSoLuongChiTietGoiMon(ChiTietGoiMon chiTietGoiMon) {
        return databaseHandler.capnhatSoLuongChiTietGoiMon(chiTietGoiMon);
    }

    @Override
    public  ChiTietGoiMon kiemTraMonDaTonTai(int maMon, int maGoiMon) {
        return databaseHandler.kiemTraMonDaTonTai(maMon,maGoiMon);
    }

    @Override
    public long xoaChiTietGoiMon(int maChiTietGoiMon) {
        return databaseHandler.xoaChiTietGoiMon(maChiTietGoiMon);
    }

    @Override
    public long xoaChiTietGoiMonByMaGoiMon(int maGoiMon) {
        return databaseHandler.xoaChiTietGoiMonByMaGoiMon(maGoiMon);
    }

    @Override
    public List<ChiTietGoiMon> listChiTietGoiMon(int maGoiMon) {
        return databaseHandler.listChiTietGoiMon(maGoiMon);
    }

    @Override
    public ChiTietGoiMon layChiTietGoiMonTheoMa(int maChiTietGoiMon) {
        return databaseHandler.layChiTietGoiMonTheoMa(maChiTietGoiMon);
    }
}
