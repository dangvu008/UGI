package dang.ugi.com.presenter.BanAn;

import android.content.Context;

import java.util.List;

import dang.ugi.com.model.BanAn.ImplBanAnDatabaseHandler;
import dang.ugi.com.model.Entities.BanAn;

/**
 * Created by DANG on 9/19/2016.
 */
public class ImplPresenterBanAn implements IPresenterBanAn {
    ImplBanAnDatabaseHandler implBanAnDatabaseHandler;

    public ImplPresenterBanAn(Context context) {
        implBanAnDatabaseHandler = new ImplBanAnDatabaseHandler(context);
    }

    @Override
    public List<BanAn> layToanBoBanAn(int maCuahang) {
        return implBanAnDatabaseHandler.layToanBoBanAn(maCuahang);
    }

    @Override
    public List<String> layToanBoTenBanAn(int maCuaHang) {
        return implBanAnDatabaseHandler.layToanBoTenBanAn(maCuaHang);
    }

    @Override
    public List<BanAn> listAllChuyenBan(int maCuaHang, int maban) {
        return implBanAnDatabaseHandler.listAllChuyenBan(maCuaHang,maban);
    }

    @Override
    public List<BanAn> timKiemBanAnBangTen(String tenBanAn,int maCuaHang) {
        return implBanAnDatabaseHandler.timKiemBanAnBangTen(tenBanAn,maCuaHang);
    }

    @Override
    public List<BanAn> timKiemBanAnBangTen(String tenBanAn, int maCuaHang, int maBan) {
        return implBanAnDatabaseHandler.timKiemBanAnBangTen(tenBanAn,maCuaHang,maBan);
    }

    @Override
    public BanAn layBanAnbyMaBanAn(int maBan) {
        return implBanAnDatabaseHandler.layBanAnbyMaBanAn(maBan);
    }

    @Override
    public boolean kiemTraTonTaiBanAn(String tenBan, int maCuahang) {
        return implBanAnDatabaseHandler.kiemTraTenBanTonTai(tenBan,maCuahang);
    }

    @Override
    public long capNhatTrangThaiBan(int maBan, int trangThai) {
        return implBanAnDatabaseHandler.capNhatTrangThaiBan(maBan,trangThai);
    }

    @Override
    public long themBanAn(BanAn banAn) {
        return implBanAnDatabaseHandler.themBanAn(banAn);
    }

    @Override
    public long suaBanAn(BanAn banAn) {
        return implBanAnDatabaseHandler.suaBanAn(banAn);
    }

    @Override
    public long xoaBanAn(int maBan) {
        return implBanAnDatabaseHandler.xoaBanAn(maBan);
    }

    @Override
    public int demSoBan(int maCuaHang) {
        return implBanAnDatabaseHandler.demSoBan(maCuaHang);
    }
}
