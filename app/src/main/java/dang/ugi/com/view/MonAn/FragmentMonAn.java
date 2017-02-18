package dang.ugi.com.view.MonAn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.adapter.MonAnAdapter;
import dang.ugi.com.adapter.MonAnFragmentAdapter;
import dang.ugi.com.model.Entities.LoaiMon;
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
    public FragmentMonAn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.fragment_mon_an, container, false);
       recyclerViewMonAnTheoLoai = (RecyclerView) view.findViewById(R.id.recyerView_monan);
        MonAnFragmentAdapter monAnFragmentAdapter = new MonAnFragmentAdapter(getActivity());
        recyclerViewMonAnTheoLoai.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPX(5), true));
        recyclerViewMonAnTheoLoai.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMonAnTheoLoai.setAdapter(monAnFragmentAdapter);
        recyclerViewMonAnTheoLoai.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onClick(View view) {
    }
}
