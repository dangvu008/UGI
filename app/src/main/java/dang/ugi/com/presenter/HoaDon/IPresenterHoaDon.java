package dang.ugi.com.presenter.HoaDon;

import java.util.List;

import dang.ugi.com.model.Entities.HoaDon;

/**
 * Created by DANG on 12/12/2016.
 */

public interface IPresenterHoaDon {
    long themHoaDon(HoaDon hoaDon);
    List<HoaDon> listAllHoaDon();
    float doanhThuNgay(String ngay, int maCuaHang);
    float doanhThuThang(String thang, int maCuaHang);
    float doanhThuNam(String nam, int maCuaHang);
    long xoaHoaDon(int maHoaDon);
    float doanhThuTheoBan(int maBan);
}
