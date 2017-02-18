package dang.ugi.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.LoaiMon;
import dang.ugi.com.model.Entities.MonAn;
import dang.ugi.com.presenter.MonAn.ImpPresenterMonAn;

/**
 * Created by DANG on 10/26/2016.
 */

public class LoaiMonAnAdapter extends RecyclerView.Adapter<LoaiMonAnAdapter.LoaiMonAnViewHolder>{

    Context context;
    List<LoaiMon> list;
    ImpPresenterMonAn impPresenterMonAn;
    public LoaiMonAnAdapter(Context context, List<LoaiMon> list) {
        this.context = context;
        this.list = list;
        impPresenterMonAn = new ImpPresenterMonAn(context);
    }

    @Override
    public LoaiMonAnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_monan_theoloai,parent,false);
        return new LoaiMonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LoaiMonAnViewHolder holder, int position) {
        LoaiMon loaiMon = list.get(position);
        int maLoaiMon = loaiMon.getMaLoaiMon();
        String tenLoaiMon = loaiMon.getTenLoaiMon();
        holder.textViewTenLoaiMon.setText(tenLoaiMon);
        List<MonAn> listMonAn = impPresenterMonAn.listAllMonAnTheoMaLoai(maLoaiMon);
        MonAnAdapter monAnAdapter = new MonAnAdapter(context,listMonAn,R.layout.custom_cardview_monan,1);
        holder.recyclerViewMonAnTheoLoai.setAdapter(monAnAdapter);
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    public class LoaiMonAnViewHolder extends  RecyclerView.ViewHolder{
        RecyclerView recyclerViewMonAnTheoLoai;
        TextView textViewTenLoaiMon;
        Button btnLoadmore;

        public LoaiMonAnViewHolder(View itemView) {
            super(itemView);
            recyclerViewMonAnTheoLoai = (RecyclerView) itemView.findViewById(R.id.recyclerViewListMon);
            textViewTenLoaiMon = (TextView) itemView.findViewById(R.id.textViewTenLoaiMon);
            btnLoadmore = (Button) itemView.findViewById(R.id.btnLoadMoreMonAn);
        }
    }
}
