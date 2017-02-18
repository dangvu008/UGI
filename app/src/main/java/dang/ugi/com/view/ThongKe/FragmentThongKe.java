package dang.ugi.com.view.ThongKe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import dang.ugi.com.R;
import dang.ugi.com.model.Utils.FormatData;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.HoaDon.ImplPresenterHoaDon;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThongKe extends Fragment {

    ImplPresenterHoaDon implPresenterHoaDon;
    TextView textViewNgay,textViewDoanhThuNgay,textViewDoanhThuThang,textViewDoanhThuNam;
    float doanhthungay,doanhthuthang,doanhthunam;
    public FragmentThongKe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        textViewNgay = (TextView) view.findViewById(R.id.textView_thongke_ngay);
        textViewDoanhThuNgay = (TextView) view.findViewById(R.id.textView_thongke_doanhthu_homnay);
        textViewDoanhThuThang = (TextView) view.findViewById(R.id.textView_thongke_doanhthuthang);
        textViewDoanhThuNam = (TextView) view.findViewById(R.id.textView_thongke_doanhthunam);
        implPresenterHoaDon = new ImplPresenterHoaDon(getActivity());
        loadData();
        return view;
    }

    private void loadData() {
        int maCuahang = PrefNhaHang.layCuaHangHienTai(getActivity()).getMaCuaHang();
        Calendar cal = Calendar.getInstance();
        String strDate = FormatData.formatDateVietNam(cal.getTime());
        String strMonth = FormatData.formatMonthVietNam(cal.getTime());
        String strYear = FormatData.formatYearVietNam(cal.getTime());
        doanhthungay = implPresenterHoaDon.doanhThuNgay(strDate,maCuahang);
        doanhthuthang = implPresenterHoaDon.doanhThuThang(strMonth,maCuahang);
        doanhthunam = implPresenterHoaDon.doanhThuNam(strYear,maCuahang);

        textViewNgay.setText(strDate);
        textViewDoanhThuNgay.setText(FormatData.formatMoneyVietNam(doanhthungay) +" VND");
        textViewDoanhThuThang.setText(FormatData.formatMoneyVietNam(doanhthuthang) +" VND");
        textViewDoanhThuNam.setText(FormatData.formatMoneyVietNam(doanhthunam) +" VND");
    }

}
