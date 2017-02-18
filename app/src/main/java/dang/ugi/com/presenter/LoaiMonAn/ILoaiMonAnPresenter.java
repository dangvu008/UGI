package dang.ugi.com.presenter.LoaiMonAn;

import java.util.List;

import dang.ugi.com.model.Entities.LoaiMon;

/**
 * Created by DANG on 9/26/2016.
 */

public interface ILoaiMonAnPresenter {
    List<LoaiMon> listLoaiMon();
    List<String> listTenLoaiMon();
    List<LoaiMon> listLoaiMonTheoCuaHang(int maCuaHang);
    LoaiMon layLoaiMonById(int maLoaiMon);
    long themLoaiMon(LoaiMon loaiMon);
    long CapNhatLoaiMon(LoaiMon loaiMon);
    long xoaLoaiMon(int maLoaiMon);
    boolean kiemtraLoaiMonTonTai(String tenLoaiMon);
}
