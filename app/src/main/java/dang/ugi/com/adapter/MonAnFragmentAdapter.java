package dang.ugi.com.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.LoaiMon;
import dang.ugi.com.model.Entities.MonAn;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.LoaiMonAn.ImplLoaiMonAnPresenter;
import dang.ugi.com.presenter.MonAn.ImpPresenterMonAn;
import dang.ugi.com.view.MonAn.FragmentListMonAnTheoLoai;
import dang.ugi.com.view.TrangChu.MainActivity;

/**
 * Created by DANG on 11/7/2016.
 */

public class MonAnFragmentAdapter extends RecyclerView.Adapter<MonAnFragmentAdapter.MonAnFragmentViewHolder>{
    private List<LoaiMon> listLoaiMon;
    private List<MonAn> listMonAn;
    Context context;
    ImpPresenterMonAn impPresenterMonAn;
    ImplLoaiMonAnPresenter implLoaiMonAnPresenter;
    int maCuaHang;
    CuaHang cuaHang;
    public MonAnFragmentAdapter(Context context) {
        this.context = context;
        impPresenterMonAn = new ImpPresenterMonAn(context);
        cuaHang = PrefNhaHang.layCuaHangHienTai(context);
        implLoaiMonAnPresenter = new ImplLoaiMonAnPresenter(context);
       if (cuaHang!=null)
           maCuaHang = cuaHang.getMaCuaHang();
        listLoaiMon = implLoaiMonAnPresenter.listLoaiMonTheoCuaHang(maCuaHang);
    }

    @Override
    public MonAnFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null ;
        if (view==null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_danhsach_monan_theoloai,parent,false);
        }

        return new MonAnFragmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MonAnFragmentViewHolder holder, int position) {
        MonAnFragmentViewHolder viewHolder = holder;
        LoaiMon loaiMon = listLoaiMon.get(position);
        viewHolder.textViewTenLoaiMon.setText(loaiMon.getTenLoaiMon());
        final int maLoaiMon = loaiMon.getMaLoaiMon();
        listMonAn = impPresenterMonAn.listAllMonAnTheoMaLoai(maLoaiMon);
        MonAnAdapter monAnAdapter = new MonAnAdapter(context,listMonAn,R.layout.custom_cardview_monan,1);
        viewHolder.recyclerViewDanhSachMonTheoLoai.setAdapter(monAnAdapter);
      viewHolder.recyclerViewDanhSachMonTheoLoai.setHasFixedSize(true);
        viewHolder.recyclerViewDanhSachMonTheoLoai.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        viewHolder.btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentListMonAnTheoLoai fragmentListMonAnTheoLoai = new FragmentListMonAnTheoLoai();
               if (context instanceof MainActivity){
                   Bundle bundle = new Bundle();
                   bundle.putInt("maLoai",maLoaiMon);
                   fragmentListMonAnTheoLoai.setArguments(bundle);
                  FragmentTransaction transaction = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
                   transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                   transaction.replace(R.id.fl_content, fragmentListMonAnTheoLoai, MainActivity.TAG_THUCDON);
                   transaction.commitNowAllowingStateLoss();
               }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listLoaiMon!=null ? listLoaiMon.size():0;
    }

    public class MonAnFragmentViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewTenLoaiMon;
        private Button btnLoadMore;
        private RecyclerView recyclerViewDanhSachMonTheoLoai;
        public MonAnFragmentViewHolder(View itemView) {
            super(itemView);
            textViewTenLoaiMon = (TextView) itemView.findViewById(R.id.itemTitle);
            btnLoadMore = (Button) itemView.findViewById(R.id.btnMore);
            recyclerViewDanhSachMonTheoLoai = (RecyclerView) itemView.findViewById(R.id.recycler_view_list);
        }
    }

}
