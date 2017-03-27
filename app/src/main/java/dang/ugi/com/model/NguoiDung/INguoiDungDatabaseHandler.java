package dang.ugi.com.model.NguoiDung;

import java.util.List;

import dang.ugi.com.model.Entities.NguoiDung;

/**
 * Created by DANG on 9/9/2016.
 */
public interface INguoiDungDatabaseHandler {
    long themNguoiDung(NguoiDung nguoiDung);
    long capNhapNguoiDung(NguoiDung nguoiDung);
    long capNhatMatKhau(int maNguoiDung,String matKhau);
    List<NguoiDung> listAllNguoiDung();
    List<NguoiDung> listAllNguoiDung(int maCuaHang);
    List<NguoiDung> listUnsyncNguoiDung(int maCuaHang);
    NguoiDung layNguoiDungById(int maNguoiDung);
    boolean kiemTraEmailTonTai(String email);
    boolean kiemTraSoDienThoaiTonTai(String sodienthoai);
    boolean kiemTraTenTonTai(String ten);
    boolean xoaNguoiDung(int maNguoiDung);
    boolean updateNguoiDung(NguoiDung nguoiDung);
    NguoiDung dangNhap(String account,String matKhau);
    int lastIdNguoiDung();
    NguoiDung checkAllowLogin(String email);
    long capNhatTrangThaiNguoiDung(int MaNguoiDung,int trangThai);
    List<NguoiDung> timKiemNguoiDung(String keySearch,int maCuaHang);
}
