package dang.ugi.com.model.Entities;

/**
 * Created by DANG on 11/19/2016.
 */

public class ChiTietGoiMon {
    private int maChiTietGoiMon;
    private int maGoiMon;
    private int maMon;
    private int soLuong;
    private float thanhTien;

    public ChiTietGoiMon() {
    }

    public int getMaChiTietGoiMon() {
        return maChiTietGoiMon;
    }

    public void setMaChiTietGoiMon(int maChiTietGoiMon) {
        this.maChiTietGoiMon = maChiTietGoiMon;
    }

    public int getMaGoiMon() {
        return maGoiMon;
    }

    public void setMaGoiMon(int maGoiMon) {
        this.maGoiMon = maGoiMon;
    }

    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }
}
