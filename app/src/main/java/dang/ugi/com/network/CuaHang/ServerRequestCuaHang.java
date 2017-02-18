package dang.ugi.com.network.CuaHang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.NguoiDung;

/**
 * Created by DANG on 12/26/2016.
 */

public class ServerRequestCuaHang {

    @SerializedName("operation")
    @Expose
    private String operation;
    @SerializedName("CuaHang")
    @Expose
    private CuaHang cuaHang;
    @SerializedName("NguoiDung")
    @Expose
    private NguoiDung nguoiDung;
    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setCuaHang(CuaHang cuaHang) {
        this.cuaHang = cuaHang;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }
}
