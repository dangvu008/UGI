package dang.ugi.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.ChiTietGoiMon;
import dang.ugi.com.model.Entities.MonAn;
import dang.ugi.com.presenter.MonAn.ImpPresenterMonAn;

/**
 * Created by DANG on 12/5/2016.
 */

public class ThanhToanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ChiTietGoiMon> listChiTietGoiMon;
    private ChiTietGoiMon chiTietGoiMon;
    private ImpPresenterMonAn impPresenterMonAn;

    public ThanhToanAdapter(Context context, List<ChiTietGoiMon> listChiTietGoiMon) {
        this.context = context;
        this.listChiTietGoiMon = listChiTietGoiMon;
        impPresenterMonAn = new ImpPresenterMonAn(context);

    }

    @Override
    public long getItemId(int position) {
        return listChiTietGoiMon.get(position).getMaChiTietGoiMon();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_recyclerview_list_mon_danggoi, parent, false);
        return new ThanhToanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ThanhToanViewHolder viewHolder = (ThanhToanViewHolder) holder;
        chiTietGoiMon = listChiTietGoiMon.get(position);
        final MonAn monAn = impPresenterMonAn.layMonAnbyId(chiTietGoiMon.getMaMon());
        NumberFormat numberFormat = new DecimalFormat("###.###");
        String strThanhTien = numberFormat.format(chiTietGoiMon.getThanhTien()) + " VND";
        int soLuong = chiTietGoiMon.getSoLuong();
        viewHolder.textViewTenMon.setText(monAn.getTenMonAn());
        viewHolder.textViewTongTien.setText(context.getString(R.string.thanhtien) + strThanhTien);
        viewHolder.textViewSoLuong.setText(context.getString(R.string.soluong) + String.valueOf(soLuong));

    }

    @Override
    public int getItemCount() {
        return (listChiTietGoiMon != null ? listChiTietGoiMon.size() : 0);
    }

    public class ThanhToanViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewSoLuong, textViewTongTien, textViewTenMon;

        public ThanhToanViewHolder(View itemView) {
            super(itemView);
            textViewTenMon = (TextView) itemView.findViewById(R.id.textView_item_hoadon_TenMon);
            textViewSoLuong = (TextView) itemView.findViewById(R.id.textView_item_hoadon_SoLuong);
            textViewTongTien = (TextView) itemView.findViewById(R.id.textView_item_hoadon_thanhtien);
        }
    }
}
