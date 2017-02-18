package dang.ugi.com.network.NguoiDung;

import java.util.List;

import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.NguoiDung;

/**
 * Created by DANG on 12/26/2016.
 */

public class ServerResponseNguoiDung {
    private String result;
    private String message;
    private NguoiDung nguoiDung;
    private CuaHang cuaHang;
    private List<NguoiDung> listNguoiDung;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public CuaHang getCuaHang() {
        return cuaHang;
    }

    public List<NguoiDung> getListNguoiDung() {
        return listNguoiDung;
    }
}
