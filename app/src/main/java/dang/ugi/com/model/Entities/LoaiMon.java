package dang.ugi.com.model.Entities;

import java.io.Serializable;

/**
 * Created by DANG on 9/26/2016.
 */

public class LoaiMon implements Serializable{
    private int maLoaiMon;
    private String tenLoaiMon;

    public LoaiMon() {
    }

    public int getMaLoaiMon() {
        return maLoaiMon;
    }

    public void setMaLoaiMon(int maLoaiMon) {
        this.maLoaiMon = maLoaiMon;
    }

    public String getTenLoaiMon() {
        return tenLoaiMon;
    }

    public void setTenLoaiMon(String tenLoaiMon) {
        this.tenLoaiMon = tenLoaiMon;
    }
}
