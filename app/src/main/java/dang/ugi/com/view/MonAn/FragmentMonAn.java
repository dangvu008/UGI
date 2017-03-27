package dang.ugi.com.view.MonAn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.adapter.MonAnAdapter;
import dang.ugi.com.asyntask.LoadMonAnSynTask;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.LoaiMon;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.LoaiMonAn.ImplLoaiMonAnPresenter;
import dang.ugi.com.presenter.MonAn.ImpPresenterMonAn;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMonAn extends Fragment implements View.OnClickListener{

    MonAnAdapter adapter;
    List<LoaiMon> listLoaiMon;
    RecyclerView recyclerViewMonAnTheoLoai;
    ImpPresenterMonAn impPresenterMonAn;
    ImplLoaiMonAnPresenter implLoaiMonAnPresenter;
    View view;
    private int maCuaHang;
private Bundle bundle;
    public FragmentMonAn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_mon_an, container, false);
        CuaHang cuaHangHT = PrefNhaHang.layCuaHangHienTai(getActivity());
        if (cuaHangHT != null)
            maCuaHang = cuaHangHT.getMaCuaHang();
        bundle = getArguments();
        if (bundle != null) {
            String key = bundle.getString("keySearch");
        } else {
            new LoadMonAnSynTask(view).execute();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bundle==null)
            new LoadMonAnSynTask(view).execute();
    }

    @Override
    public void onClick(View view) {
    }
}
