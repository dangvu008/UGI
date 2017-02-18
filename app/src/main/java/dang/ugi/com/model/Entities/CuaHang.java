package dang.ugi.com.model.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DANG on 9/27/2016.
 */

public class CuaHang {
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("MaCuaHang")
    @Expose
    private int maCuaHang;
    @SerializedName("TenCuahang")
    @Expose
    private String tenCuahang;
    @SerializedName("MaLoaiCuaHang")
    @Expose
    private int maLoaiCuaHang;
    @SerializedName("Logo")
    @Expose
    private String logo;
    @SerializedName("DiaChi")
    @Expose
    private String diaChi;
    @SerializedName("SoDienThoai")
    @Expose
    private String soDienThoai;

    public CuaHang() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaCuaHang() {
        return maCuaHang;
    }

    public void setMaCuaHang(int maCuaHang) {
        this.maCuaHang = maCuaHang;
    }

    public String getTenCuahang() {
        return tenCuahang;
    }

    public void setTenCuahang(String tenCuahang) {
        this.tenCuahang = tenCuahang;
    }

    public int getMaLoaiCuaHang() {
        return maLoaiCuaHang;
    }

    public void setMaLoaiCuaHang(int maLoaiCuaHang) {
        this.maLoaiCuaHang = maLoaiCuaHang;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
