package dang.ugi.com.presenter.CuaHang;

import java.util.List;

import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.LoaiCuaHang;

/**
 * Created by DANG on 9/28/2016.
 */

public interface IPresenterCuaHang {
    List<CuaHang> layToanBoCuaHang(int maNguoiDung);
    List<CuaHang> timKiemCuahang(String tenCuaHang,int maNguoiDung);
    CuaHang layCuahangById(int maCuaHang);
    boolean kiemTratenCuahang(String tenCuaHang);
    long themCuaHang(CuaHang cuaHang);
    long themNguoiDungCuaHang(int maNguoiDung,int maCuaHang);
    long suaCuaHang(CuaHang cuaHang);
    long xoaCuaHang(int maCuaHang);
    long themCuaHangNguoiDung(int maCuaHang,int maNguoiDung);
    int  layMaCuaHangByMaNguoiDung(int maNguoiDung);
    int themLoaiCuaHang(String tenLoaiCuaHang);
    CuaHang checkCuaHangNguoiDung(int maNguoiDung);
    List<LoaiCuaHang> layLoaiCuaHang();
}
