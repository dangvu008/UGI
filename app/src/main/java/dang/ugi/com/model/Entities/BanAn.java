package dang.ugi.com.model.Entities;

import java.io.Serializable;

/**
 * Created by DANG on 9/18/2016.
 */
public class BanAn implements Serializable {
    private int maBanAn;
    private String tenBanAn;
    private int maCuaHang;
    private int tinhTrang;

    public BanAn() {
    }

    public BanAn(String tenBanAn, int maCuaHang, int tinhTrang) {
        this.tenBanAn = tenBanAn;
        this.maCuaHang = maCuaHang;
        this.tinhTrang = tinhTrang;
    }

    public int getMaBanAn() {
        return maBanAn;
    }

    public void setMaBanAn(int maBanAn) {
        this.maBanAn = maBanAn;
    }

    public String getTenBanAn() {
        return tenBanAn;
    }

    public void setTenBanAn(String tenBanAn) {
        this.tenBanAn = tenBanAn;
    }

    public int getMaCuaHang() {
        return maCuaHang;
    }

    public void setMaCuaHang(int maCuaHang) {
        this.maCuaHang = maCuaHang;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
