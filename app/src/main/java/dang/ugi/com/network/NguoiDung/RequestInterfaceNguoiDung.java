package dang.ugi.com.network.NguoiDung;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by DANG on 12/26/2016.
 */

public interface RequestInterfaceNguoiDung {
    @POST("/ugi/NguoiDung/")
    Call<ServerResponseNguoiDung> operation(@Body ServerRequestNguoiDung request);
}
