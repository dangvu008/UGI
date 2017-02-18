package dang.ugi.com.model.Entities;

import java.util.Date;

/**
 * Created by DANG on 9/27/2016.
 */

public class DoanhThuMonAn {
    private int maDoanhThuMonAn;
    private int maCuaHang;
    private int maMon;
    private Date thoiGian;
    private double doanhThuMonAn;

    public DoanhThuMonAn() {
    }

    public int getMaDoanhThuMonAn() {
        return maDoanhThuMonAn;
    }

    public void setMaDoanhThuMonAn(int maDoanhThuMonAn) {
        this.maDoanhThuMonAn = maDoanhThuMonAn;
    }

    public int getMaCuaHang() {
        return maCuaHang;
    }

    public void setMaCuaHang(int maCuaHang) {
        this.maCuaHang = maCuaHang;
    }

    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public double getDoanhThuMonAn() {
        return doanhThuMonAn;
    }

    public void setDoanhThuMonAn(double doanhThuMonAn) {
        this.doanhThuMonAn = doanhThuMonAn;
    }
}
