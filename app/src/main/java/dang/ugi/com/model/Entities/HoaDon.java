package dang.ugi.com.model.Entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DANG on 12/9/2016.
 */

public class HoaDon implements Serializable {
    private String maHoaDon;
    private int maGoiMon;
    private float tienthem;
    private float sotien;
    private int maNguoiDungThanhToan;
    private Date thoiGianThanhToan;
    private String ghiChu;
    public HoaDon() {
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaGoiMon() {
        return maGoiMon;
    }

    public void setMaGoiMon(int maGoiMon) {
        this.maGoiMon = maGoiMon;
    }

    public double getSotien() {
        return sotien;
    }

    public void setSotien(float sotien) {
        this.sotien = sotien;
    }

    public double getTienthem() {
        return tienthem;
    }

    public void setTienthem(float tienthem) {
        this.tienthem = tienthem;
    }

    public int getMaNguoiDungThanhToan() {
        return maNguoiDungThanhToan;
    }

    public void setMaNguoiDungThanhToan(int maNguoiDungThanhToan) {
        this.maNguoiDungThanhToan = maNguoiDungThanhToan;
    }

    public Date getThoiGianThanhToan() {
        return thoiGianThanhToan;
    }

    public void setThoiGianThanhToan(Date thoiGianThanhToan) {
        this.thoiGianThanhToan = thoiGianThanhToan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
