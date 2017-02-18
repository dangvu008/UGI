package dang.ugi.com.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.ChiTietGoiMon;
import dang.ugi.com.model.Entities.MonAn;
import dang.ugi.com.presenter.MonAn.ImpPresenterMonAn;

/**
 * Created by DANG on 11/21/2016.
 */

public class ChiTietGoiMonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   private Context context;
   private List<ChiTietGoiMon> listChiTietGoiMon;
   private ChiTietGoiMon chiTietGoiMon;
    private MonAn monAn;
    private ImpPresenterMonAn impPresenterMonAn;
    public ChiTietGoiMonAdapter(Context context,List<ChiTietGoiMon> listChiTietGoiMon) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_recyclerview_list_mon_danggoi,parent,false);
        return new ChiTietGoiMonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChiTietGoiMonViewHolder viewHolder = (ChiTietGoiMonViewHolder) holder;
        chiTietGoiMon = listChiTietGoiMon.get(position);
        monAn = impPresenterMonAn.layMonAnbyId(chiTietGoiMon.getMaMon());
        NumberFormat numberFormat = new DecimalFormat("###.###");
        float gia = impPresenterMonAn.layGiaMonTheoMaMon(monAn.getMaMonAn());
        String strGia = numberFormat.format(gia) +" VND";
        String strThanhTien = numberFormat.format(chiTietGoiMon.getThanhTien()) + " VND";
        int soLuong = chiTietGoiMon.getSoLuong();
        viewHolder.textViewTenMon.setText(monAn.getTenMonAn());
        viewHolder.textViewGia.setText("Giá : " + strGia );
        viewHolder.textViewTongTien.setText(context.getString(R.string.thanhtien) + strThanhTien);
        viewHolder.textViewSoLuong.setText(context.getString(R.string.soluong)  + String.valueOf(soLuong));
        if (monAn.getHinhAnh()=="null"){
            viewHolder.imageViewHinhAnhMon.setImageResource(R.drawable.coffe_default);
        }else{
            viewHolder.imageViewHinhAnhMon.setImageURI(Uri.parse(monAn.getHinhAnh()));
        }
    }

    @Override
    public int getItemCount() {
        return (listChiTietGoiMon!=null?listChiTietGoiMon.size():0);
    }
    public class ChiTietGoiMonViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewHinhAnhMon;
        private TextView textViewGia,textViewSoLuong,textViewTongTien,textViewTenMon;
        public ChiTietGoiMonViewHolder(View itemView) {
            super(itemView);
            textViewTenMon = (TextView) itemView.findViewById(R.id.textview_item_goimon_tenMon);
            textViewGia = (TextView) itemView.findViewById(R.id.textview_item_goimon_gia);
            textViewSoLuong = (TextView) itemView.findViewById(R.id.textview_item_goimon_soluong);
            textViewTongTien = (TextView) itemView.findViewById(R.id.textview_item_goimon_thanhtien);
            imageViewHinhAnhMon = (ImageView) itemView.findViewById(R.id.imageView_item_goimon_hinhanhmon);
        }
    }
}
