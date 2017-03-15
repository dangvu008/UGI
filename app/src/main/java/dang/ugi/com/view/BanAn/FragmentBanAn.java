package dang.ugi.com.view.BanAn;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dang.ugi.com.asyntask.LoadBanAsynTask;
import dang.ugi.com.R;
import dang.ugi.com.adapter.BanAnAdapter;
import dang.ugi.com.model.Entities.BanAn;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.NguoiDung;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.BanAn.ImplPresenterBanAn;

/**
 * Created by DANG on 9/23/2016.
 */

public class FragmentBanAn extends Fragment {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private BanAnAdapter banAnAdapter;
    private ImplPresenterBanAn implPresenterBanAn;
    private NguoiDung nguoiDung;
    private List<BanAn> listBanAn;
    private View view;
    Bundle bundle;
    int maCuaHang;
    public static final int id_loader = R.integer.id_loader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banan, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyerView_banAn_main);
        implPresenterBanAn = new ImplPresenterBanAn(getActivity());
        CuaHang cuaHangHT = PrefNhaHang.layCuaHangHienTai(getActivity());
        if (cuaHangHT != null)
            maCuaHang = cuaHangHT.getMaCuaHang();
        bundle = getArguments();
        if (bundle != null) {
            String key = bundle.getString("keySearch");
            new LoadBanAsynTask(getActivity(), view).execute(key);
        } else {
            new LoadBanAsynTask(getActivity(), view).execute("");
        }
        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bundle==null){
            new LoadBanAsynTask(getActivity(), view).execute("");
        }
        //getLoaderManager().restartLoader(1,null,this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


  /*  @Override
    public Loader<List<BanAn>> onCreateLoader(int id, Bundle args) {
        dang.ugi.com.view.BanAn.BanAnLoader banAnLoader = new dang.ugi.com.view.BanAn.BanAnLoader(getActivity(),args);
        banAnLoader.forceLoad();
        return banAnLoader;
    }


    @Override
    public void onLoadFinished(Loader<List<BanAn>> loader, List<BanAn> data) {
        loadDataToRecyerView(data);
    }

    @Override
    public void onLoaderReset(Loader<List<BanAn>> loader) {
        Log.d("Loader","onLoaderReset");
     recyclerView.setAdapter(null);
    }*/
}
