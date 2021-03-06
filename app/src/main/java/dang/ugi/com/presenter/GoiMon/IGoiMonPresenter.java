package dang.ugi.com.presenter.GoiMon;

import java.util.List;

import dang.ugi.com.model.Entities.GoiMon;

/**
 * Created by DANG on 11/19/2016.
 */

public interface IGoiMonPresenter {
    long themGoiMon(GoiMon goiMon);
    long themTamGoiMon(GoiMon goiMon);
    long capNhatGoiMon(GoiMon goiMon);
    long xoaGoiMon(int maGoiMon);
    long xoaLichSuGoiMonTam();
    List<GoiMon> listAllGoiMon(int maCuaHang);
    List<GoiMon> listAllGoiMonTheoTinhTrang(int maCuaHang,int tinhTrang);
    GoiMon layGoiMonTheoMaGoiMon(int maGoiMon);
    GoiMon layGoiMonDangGoi(int maBan);
}
