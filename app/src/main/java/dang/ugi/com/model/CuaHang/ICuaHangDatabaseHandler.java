package dang.ugi.com.model.CuaHang;

import java.util.List;

import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.LoaiCuaHang;

/**
 * Created by DANG on 9/27/2016.
 */

public interface ICuaHangDatabaseHandler {
    List<CuaHang> layToanBoCuaHang(int maNguoiDung);
    List<CuaHang> timKiemCuahang(String tenCuaHang,int maNguoiDung);
    CuaHang layCuahangById(int maCuaHang);
    boolean kiemTratenCuahang(String tenCuaHang);
    long themCuaHang(CuaHang cuaHang);
    long suaCuaHang(CuaHang cuaHang);
    long xoaCuaHang(int maCuaHang);
    long themCuaHangNguoiDung(int maCuaHang,int maNguoiDung);
    int  layMaCuaHangByMaNguoiDung(int maNguoiDung);
    int themLoaiCuaHang(String tenLoaiCuaHang);
    List<LoaiCuaHang> layLoaiCuaHang();
}