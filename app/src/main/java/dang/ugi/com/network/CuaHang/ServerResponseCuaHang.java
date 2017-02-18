package dang.ugi.com.network.CuaHang;

import dang.ugi.com.model.Entities.CuaHang;

/**
 * Created by DANG on 12/26/2016.
 */

public class ServerResponseCuaHang {
    private String result;
    private String message;
    private CuaHang cuaHang;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public CuaHang getCuaHang() {
        return cuaHang;
    }
}
