package dang.ugi.com.model.Entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DANG on 11/19/2016.
 */

public class GoiMon implements Serializable {
    private int maGoiMon;
    private int maNguoiDungGoi;
    private int maNguoiDungThanhToan;
    private int maCuaHang;
    private int maBan;
    private Date thoiGianBatDau;
    private Date thoiGianThanhToan;
    private String ghiChu;
    private float tongTien;
    private int tinhTrang;

    public GoiMon() {
    }

    public int getMaGoiMon() {
        return maGoiMon;
    }

    public void setMaGoiMon(int maGoiMon) {
        this.maGoiMon = maGoiMon;
    }

    public int getMaNguoiDungGoi() {
        return maNguoiDungGoi;
    }

    public void setMaNguoiDungGoi(int maNguoiDungGoi) {
        this.maNguoiDungGoi = maNguoiDungGoi;
    }

    public int getMaNguoiDungThanhToan() {
        return maNguoiDungThanhToan;
    }

    public void setMaNguoiDungThanhToan(int maNguoiDungThanhToan) {
        this.maNguoiDungThanhToan = maNguoiDungThanhToan;
    }

    public int getMaCuaHang() {
        return maCuaHang;
    }

    public void setMaCuaHang(int maCuaHang) {
        this.maCuaHang = maCuaHang;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public Date getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Date thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
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

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
