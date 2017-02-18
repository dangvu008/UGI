package dang.ugi.com.view.MonAn;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.view.TrangChu.RecyclerItemClickListener;
import dang.ugi.com.adapter.MonAnAdapter;
import dang.ugi.com.model.Entities.Gia;
import dang.ugi.com.model.Entities.MonAn;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.MonAn.ImpPresenterMonAn;

/**
 * Created by DANG on 11/7/2016.
 */

public class FragmentChonMonCoSan extends Fragment {
    private RecyclerView recyclerViewMonAn;
    private FloatingActionButton fb_timkiemmon, fabCancle;
    private List<MonAn> listMonAn;
    private ImpPresenterMonAn impPresenterMonAn;
    private Dialog dialog;
    private SearchView searchView;
    private MonAnAdapter monAnAdapter;
    private
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chonmoncosan, container, false);
        impPresenterMonAn = new ImpPresenterMonAn(getActivity());
        addControls(view);
        bundle = getArguments();
        if (bundle.getInt("chucnang") == 2) {
            String s = bundle.getString("keySearch");
            if (s == null) {
                listMonAn = impPresenterMonAn.listAllMonAn();
            } else {
                listMonAn = impPresenterMonAn.listTimKiemMonAn(s);
            }

        }

        loadData(listMonAn);
        recyclerViewMonAn.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int maMon = listMonAn.get(position).getMaMonAn();
                float giaThamKhao = listMonAn.get(position).getGiaThamKhao();
                int maCuaHang = PrefNhaHang.layCuaHangHienTai(getActivity()).getMaCuaHang();
                if (impPresenterMonAn.kiemtraMonDacoTrongThucDon(maMon, maCuaHang)) {
                    impPresenterMonAn.xoaBangGia(maMon, maCuaHang);
                } else {
                    Gia gia = new Gia();
                    gia.setMaMon(maMon);
                    gia.setMaCuaHang(maCuaHang);
                    gia.setGia(giaThamKhao);
                    impPresenterMonAn.themBangGia(gia);
                }
                listMonAn = impPresenterMonAn.listAllMonAn();
                monAnAdapter.refreshList(listMonAn);

            }
        }));
        return view;
    }

    private void loadData(List<MonAn> list) {
        monAnAdapter = new MonAnAdapter(getActivity(), list, R.layout.custom_cardview_monan, 2);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        recyclerViewMonAn.setLayoutManager(layoutManager);
        recyclerViewMonAn.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMonAn.setAdapter(monAnAdapter);
        monAnAdapter.notifyDataSetChanged();
    }

    private void addControls(View view) {
        recyclerViewMonAn = (RecyclerView) view.findViewById(R.id.recyclerView_listThemMon);
       /* fb_timkiemmon = (FloatingActionButton) view.findViewById(R.id.fabTimKiemMon);
        fb_timkiemmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogSearch();
            }
        });*/

    }

   /* @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int maMon = listMonAn.get(i).getMaMonAn();
        float giaThamKhao = listMonAn.get(i).getGiaThamKhao();
        int maCuaHang = PrefNhaHang.layCuaHangHienTai(getActivity()).getMaCuaHang();
        if (impPresenterMonAn.kiemtraMonDacoTrongThucDon(maMon, maCuaHang)) {
            impPresenterMonAn.xoaBangGia(maMon, maCuaHang);
        } else {
            Gia gia = new Gia();
            gia.setMaMon(maMon);
            gia.setMaCuaHang(maCuaHang);
            gia.setGia(giaThamKhao);
            impPresenterMonAn.themBangGia(gia);
        }
        monAnAdapter.refreshList(listMonAn);
    }*/
}
