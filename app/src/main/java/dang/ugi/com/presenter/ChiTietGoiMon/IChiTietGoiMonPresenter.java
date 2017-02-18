package dang.ugi.com.presenter.ChiTietGoiMon;

import java.util.List;

import dang.ugi.com.model.Entities.ChiTietGoiMon;

/**
 * Created by DANG on 11/19/2016.
 */

public interface IChiTietGoiMonPresenter {
    long themChiTietGoiMon(ChiTietGoiMon chiTietGoiMon);
    long capnhatChiTietGoiMon(ChiTietGoiMon chiTietGoiMon);
    long capnhatSoLuongChiTietGoiMon(ChiTietGoiMon chiTietGoiMon);
    ChiTietGoiMon kiemTraMonDaTonTai(int maMon, int maGoiMon);
    long xoaChiTietGoiMon(int maChiTietGoiMon);
    long xoaChiTietGoiMonByMaGoiMon(int maGoiMon);
    List<ChiTietGoiMon> listChiTietGoiMon(int maGoiMon);
    ChiTietGoiMon layChiTietGoiMonTheoMa(int maChiTietGoiMon);
}
