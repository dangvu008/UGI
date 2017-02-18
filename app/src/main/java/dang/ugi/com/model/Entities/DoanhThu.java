package dang.ugi.com.model.Entities;

import java.util.Date;

/**
 * Created by DANG on 9/27/2016.
 */

public class DoanhThu {
    private int maDoanhThu;
    private int maCuaCuaHang;
    private Date thoiGian;
    private double doanhThu;

    public DoanhThu() {
    }

    public int getMaDoanhThu() {
        return maDoanhThu;
    }

    public void setMaDoanhThu(int maDoanhThu) {
        this.maDoanhThu = maDoanhThu;
    }

    public int getMaCuaCuaHang() {
        return maCuaCuaHang;
    }

    public void setMaCuaCuaHang(int maCuaCuaHang) {
        this.maCuaCuaHang = maCuaCuaHang;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }
}
