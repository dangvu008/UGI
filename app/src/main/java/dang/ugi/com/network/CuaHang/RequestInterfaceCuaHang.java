package dang.ugi.com.network.CuaHang;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by DANG on 12/26/2016.
 */

public interface RequestInterfaceCuaHang {
    @POST("/ugi/CuaHang/")
    Call<ServerResponseCuaHang> operation(@Body ServerRequestCuaHang request);
}
