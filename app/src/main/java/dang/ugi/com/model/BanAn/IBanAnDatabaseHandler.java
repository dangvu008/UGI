package dang.ugi.com.model.BanAn;

import java.util.List;

import dang.ugi.com.model.Entities.BanAn;

/**
 * Created by DANG on 9/19/2016.
 */
public interface IBanAnDatabaseHandler {
    List<BanAn> layToanBoBanAn(int maCuaHang);
    List<String> layToanBoTenBanAn(int maCuaHang);
    List<BanAn> timKiemBanAnBangTen(String tenBanAn,int maCuaHang);
    BanAn layBanAnbyMaBanAn(int maBan);
    boolean kiemTraTenBanTonTai(String tenBan,int maCuaHang);
   // boolean kiemTraTrangThaiBan(int maBan,int maCuaHang);
    long capNhatTrangThaiBan(int maBan,int trangThai);
    List<BanAn> listAllChuyenBan(int maCuaHang, int maban);
    List<BanAn> timKiemBanAnBangTen(String tenBanAn,int maCuaHang,int maBan);
    long themBanAn(BanAn banAn);
    long suaBanAn(BanAn banAn);
    long xoaBanAn(int maBan);
    int demSoBan(int maCuaHang);
    void closedb();
}
