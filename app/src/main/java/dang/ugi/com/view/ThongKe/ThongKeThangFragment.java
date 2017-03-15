package dang.ugi.com.view.ThongKe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dang.ugi.com.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongKeThangFragment extends Fragment {

BarChart barChart;
    Calendar calendar;
    List<Integer> listNgay;
    TextView textViewThang;
    public ThongKeThangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_thong_ke_thang, container, false);
        textViewThang = (TextView) view.findViewById(R.id.textViewThang);
        barChart = (BarChart) view.findViewById(R.id.barchart_month);
        calendar = Calendar.getInstance();
        listNgay = new ArrayList<>();

        return view;
    }

}
