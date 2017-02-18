package dang.ugi.com.network.NguoiDung;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import dang.ugi.com.model.Entities.NguoiDung;

/**
 * Created by DANG on 12/26/2016.
 */

public class ServerRequestNguoiDung {

    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("NguoiDung")
    @Expose
    private NguoiDung nguoidung;
/*    @SerializedName("maCuaHang")
    @Expose
    private int maCuaHang;*/
    @SerializedName("ListNguoiDung")
    @Expose
    private List<NguoiDung> listNguoiDung;
    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setNguoiDung(NguoiDung nguoidung) {
        this.nguoidung = nguoidung;
    }

    public void setListNguoiDung(List<NguoiDung> listNguoiDung) {
        this.listNguoiDung = listNguoiDung;
    }

   /* public void setMaCuaHang(int maCuaHang) {
        this.maCuaHang = maCuaHang;
    }*/
}
