package dang.ugi.com.presenter.MonAn;

import java.util.List;

import dang.ugi.com.model.Entities.Gia;
import dang.ugi.com.model.Entities.MonAn;

/**
 * Created by DANG on 9/27/2016.
 */

public interface IpresenterMonAn {
    long themMonAn(MonAn monAn);
    long capNhatMonAn(MonAn monAn);
    List<MonAn> listAllMonAnTheoMaLoai(int maLoai);
    List<MonAn> listAllMonAnTheoCuaHang(int maCuaHang);;
    List<MonAn> listAllMonAnTheoMaLoaiChiTiet(int maLoai);
    List<MonAn> listAllMonAn();
    List<MonAn> listTimKiemMonAn(String tenMonAn);
    List<MonAn> listTimKiemMonAnTrongThucDon(String tenMonAn,int maCuaHang);
    MonAn layMonAnbyId(int id);
    long xoaMonAn(int id);
    long xoaBangGia(int maMon,int maCuaHang);
    boolean kiemTraTenMonTonTai(String tenMon);
    boolean kiemtraMonDacoTrongThucDon(int maMon,int maCuaHang);
    boolean kiemTraTenMonTonTaiTrongThucDon(String tenMon,int maCuaHang);
    long themBangGia(Gia gia);
    float layGiaMonTheoMaMon(int maMon);
    int layMaCuaHangByMaMon(int maMon);
}
