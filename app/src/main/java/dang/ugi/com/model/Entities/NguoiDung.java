package dang.ugi.com.model.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DANG on 9/8/2016.
 */
public class NguoiDung implements Serializable {

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("MaNguoiDung")
    @Expose
    private int maNguoiDung;
    @SerializedName("TenNguoiDung")
    @Expose
    private String tenNguoiDung;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("MatKhau")
    @Expose
    private String matKhau;
    @SerializedName("GioiTinh")
    @Expose
    private String gioiTinh;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("NamSinh")
    @Expose
    private String namSinh;
    @SerializedName("SoDienThoai")
    @Expose
    private String soDienThoai;
    @SerializedName("MaQuyen")
    @Expose
    private int maQuyen;
    @SerializedName("TinhTrang")
    @Expose
    private int tinhTrang;
    @SerializedName("Sync")
    @Expose
    private int sync;
    @SerializedName("AllowLogin")
    @Expose
    private int allowLogin;
    public NguoiDung() {
    }



    public NguoiDung(int maNguoiDung, String tenNguoiDung, String gioitinh, String namSinh, String email, String matKhau, String soDienThoai, int tinhTrang, int maQuyen) {
        this.maNguoiDung = maNguoiDung;
        this.tenNguoiDung = tenNguoiDung;
        this.gioiTinh = gioitinh;
        this.namSinh = namSinh;
        this.email = email;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.tinhTrang = tinhTrang;
        this.maQuyen = maQuyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getGioitinh() {
        return gioiTinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioiTinh = gioitinh;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public int getAllowLogin() {
        return allowLogin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAllowLogin(int allowLogin) {
        this.allowLogin = allowLogin;
    }
}
