package dang.ugi.com.model.LoaiMonAn;

import java.util.List;

import dang.ugi.com.model.Entities.LoaiMon;

/**
 * Created by DANG on 9/26/2016.
 */

public interface ILoaiMonAnDatbaseHandler {
    List<LoaiMon> listLoaiMon();

    List<LoaiMon> listLoaiMonTheoCuaHang(int maCuaHang);

    List<String> listTenLoaiMon();
    LoaiMon layLoaiMonById(int maLoaiMon);
    long themLoaiMon(LoaiMon loaiMon);
    long CapNhatLoaiMon(LoaiMon loaiMon);
    long xoaLoaiMon(int maLoaiMon);
    boolean kiemTraLoaiMonTonTai(String tenLoaiMon);
}
