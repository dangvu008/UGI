package dang.ugi.com.view.ThongKe;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.HoaDon.ImplPresenterHoaDon;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongKeThangFragment extends Fragment {

BarChart barChart;
    Calendar calendar;
    List<Integer> listNgay;
    List<Float> doanhthuNgay;
    List<String> ngaytrongThang;
    List<BarEntry> entries;
    TextView textViewThang;
    Spinner spinner;
    int maCuaHang;
    int date,month,year,maxDay;
    ImplPresenterHoaDon implPresenterHoaDon;
    public ThongKeThangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_thong_ke_thang, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        textViewThang = (TextView) view.findViewById(R.id.textViewThang);
        barChart = (BarChart) view.findViewById(R.id.barchart_month);
        spinner = (Spinner) view.findViewById(R.id.spinnerThang);
        calendar = Calendar.getInstance();
        listNgay = new ArrayList<>();
        doanhthuNgay = new ArrayList<>();
        ngaytrongThang = new ArrayList<>();
        CuaHang cuahang = PrefNhaHang.layCuaHangHienTai(getActivity());
        if (cuahang!=null)
            maCuaHang = cuahang.getMaCuaHang();
        implPresenterHoaDon = new ImplPresenterHoaDon(getActivity());
         date = calendar.get(Calendar.DAY_OF_MONTH);
         month = calendar.get(Calendar.MONTH);
         year = calendar.get(Calendar.YEAR);
         maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<String> listThang = new ArrayList<>();
        for (int i = 0; i <=month ; i++) {
            listThang.add("Tháng "+(i+1));
        }
        String thangHT = "Tháng "+(month+1);
        textViewThang.setText(thangHT);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,listThang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
       spinner.setSelection(month);
         entries = new ArrayList<>();
        loadToBarchart(month,year);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = position;
                loadToBarchart(month,year);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
    public void loadToBarchart(int month,int year){
        for (int i = 1; i <= date; i++) {
            listNgay.add(i);
            String ngay = i+"/"+(month+1)+"/"+year ;
            ngaytrongThang.add(ngay);
            entries.add(new BarEntry(i,implPresenterHoaDon.doanhThuNgay(ngay,maCuaHang)));
        }
        BarDataSet dataSet = new BarDataSet(entries,"doanhthu");
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f); // set custom bar width
        barChart.setData(data);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate();
    }

}
