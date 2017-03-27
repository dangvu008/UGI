package dang.ugi.com.presenter.NguoiDung;

import com.facebook.AccessToken;

import java.util.List;

import dang.ugi.com.model.Entities.NguoiDung;

/**
 * Created by DANG on 9/8/2016.
 */
public interface INguoiDungPresenter {


    long themDuLieuNguoiDung(NguoiDung nguoiDung);
    boolean kiemtraTaiKhoanTonTai(String email);
    boolean kiemTraSoDienthoaiTonTai(String sodienthoai);
    boolean kiemTraTenTonTai(String ten);
    boolean xoaNguoiDung(int maNguoiDung);
    int lastIdNguoiDung();
    boolean updateNguoiDung(NguoiDung nguoiDung);

    AccessToken layTokenHienTai();
    NguoiDung dangNhap(String account,String matKhau);
    List<NguoiDung> listAllNguoiDung();
    List<NguoiDung> listAllNguoiDung(int maCuaHang);
    List<NguoiDung> listUnsyncNguoiDung(int maCuaHang);
    List<NguoiDung> timKiemNguoiDung(String keySearch,int maCuaHang);
    NguoiDung checkAllowLogin(String email);
    void destroyAccessToken();
    String setErrorEmail();
    String setErrorMatKhau();
    void chuyenManHinhChinh();
}
