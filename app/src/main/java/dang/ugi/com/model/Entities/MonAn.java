package dang.ugi.com.model.Entities;

import java.io.Serializable;

/**
 * Created by DANG on 9/26/2016.
 */

public class MonAn implements Serializable {
    private int maMonAn;
    private int maLoaiMon;
    private String tenMonAn;
    private String hinhAnh;
    private float giaThamKhao;
    public MonAn() {
    }

    public int getMaMonAn() {
        return maMonAn;
    }

    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

    public int getMaLoaiMon() {
        return maLoaiMon;
    }

    public void setMaLoaiMon(int maLoaiMon) {
        this.maLoaiMon = maLoaiMon;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public float getGiaThamKhao() {
        return giaThamKhao ;
    }

    public void setGiaThamKhao(float giaThamKhao) {
        this.giaThamKhao = giaThamKhao;
    }
}
