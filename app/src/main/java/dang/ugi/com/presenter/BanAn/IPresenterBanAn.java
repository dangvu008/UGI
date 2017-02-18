package dang.ugi.com.presenter.BanAn;

import java.util.List;

import dang.ugi.com.model.Entities.BanAn;

/**
 * Created by DANG on 9/19/2016.
 */
public interface IPresenterBanAn {
    List<BanAn> layToanBoBanAn(int maCuaHang);
    List<String> layToanBoTenBanAn(int maCuaHang);
    List<BanAn> listAllChuyenBan(int maCuaHang, int maban);
    List<BanAn> timKiemBanAnBangTen(String tenBanAn,int maCuaHang);
    List<BanAn> timKiemBanAnBangTen(String tenBanAn,int maCuaHang,int maBan);
    BanAn layBanAnbyMaBanAn(int maBan);
    boolean kiemTraTonTaiBanAn(String tenBan, int maCuahang);
    long capNhatTrangThaiBan(int maBan,int trangThai);
    long themBanAn(BanAn banAn);
    long suaBanAn(BanAn banAn);
    long xoaBanAn(int maBan);
    int demSoBan(int maCuaHang);
}
