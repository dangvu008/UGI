package dang.ugi.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.MonAn;
import dang.ugi.com.model.Utils.PrefDangNhap;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.MonAn.ImpPresenterMonAn;
import dang.ugi.com.view.MonAn.ThemMonActivity;

/**
 * Created by DANG on 10/2/2016.
 */

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.MonAnViewHolder> implements View.OnClickListener{
    Context context;
    List<MonAn> list;
    int layoutId;
    MonAnViewHolder viewHolder;
    ImpPresenterMonAn impPresenterMonAn;
    int typeDisplay;
    int chucnang,maCuaHang ;
    float gia;
    MonAn monAn;
    public static final int REQUEST_CODE_SUAMONAN = R.integer.request_suaMonAn;
    public MonAnAdapter(Context context, List<MonAn> list, int layoutId,int chucnang) {
        this.context = context;
        this.list = list;
        this.layoutId = layoutId;
        this.chucnang = chucnang;
        impPresenterMonAn= new ImpPresenterMonAn(context);
        maCuaHang = PrefNhaHang.layCuaHangHienTai(context).getMaCuaHang();
    }


    @Override
    public MonAnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
        return new MonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MonAnViewHolder holder, int position) {
        final MonAnViewHolder viewHolder = holder;
        monAn = list.get(position);
        if (monAn.getHinhAnh().equals("null")){
            holder.img_monan_hinhanhMon.setImageResource(R.drawable.coffe_default);
        }else{
            holder.img_monan_hinhanhMon.setImageURI(Uri.parse(monAn.getHinhAnh()));
        }
        if (PrefDangNhap.layNguoiDungHienTai(context).getMaQuyen()!=1 || chucnang ==2){
            holder.img_monan_menu.setVisibility(View.INVISIBLE);
        }else{
            holder.img_monan_menu.setVisibility(View.VISIBLE);
        }
        if (chucnang == 2 && impPresenterMonAn.kiemtraMonDacoTrongThucDon(monAn.getMaMonAn(),maCuaHang)){
            holder.img_monan_choose.setVisibility(View.VISIBLE);
            holder.view.setVisibility(View.VISIBLE);
        }else{
            holder.img_monan_choose.setVisibility(View.INVISIBLE);
            holder.view.setVisibility(View.INVISIBLE);
        }

       if (chucnang==2){
           gia = monAn.getGiaThamKhao();
       }else{
           gia = (float) impPresenterMonAn.layGiaMonTheoMaMon(monAn.getMaMonAn());
       }
        String tenMon = monAn.getTenMonAn();
        NumberFormat format = new DecimalFormat("###,###");
        String strGia = format.format(gia) + " VNĐ";
        viewHolder.textView_monan_giaMon.setText(strGia);
        viewHolder.textView_monan_tenMon.setText(tenMon);
        viewHolder.textView_monan_tenMon.setSelected(true);
        viewHolder.textView_monan_tenMon.setSingleLine(true);
       viewHolder.textView_monan_tenMon.setMarqueeRepeatLimit(-1);
        viewHolder.textView_monan_tenMon.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        holder.img_monan_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(viewHolder.img_monan_menu);
            }
        });

    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(context,view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.menu_cardview_banan,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_menu_sua:
                        suaMonAn(monAn);
                        break;
                    case R.id.item_menu_xoa:
                        xoaMonAn(monAn.getMaMonAn());
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void xoaMonAn(int maMonAn) {
        if (impPresenterMonAn.xoaMonAn(maMonAn)!=-1){
           refreshList(list);
        }
    }

    private void suaMonAn(MonAn monAn) {
        Intent intent = new Intent(context, ThemMonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bMonAn",monAn);
        intent.putExtra("MONAN",bundle);
        ((Activity)context).startActivityForResult(intent,REQUEST_CODE_SUAMONAN);
    }
    @Override
    public long getItemId(int i) {
        return list.get(i).getMaMonAn();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    


    @Override
    public void onClick(View view) {

    }
    public void refreshList(List<MonAn> listMonAn){
        this.list.clear();
        this.list.addAll(listMonAn);
        this.notifyDataSetChanged();
    }
    public class MonAnViewHolder extends RecyclerView.ViewHolder{
        ImageView img_monan_hinhanhMon,img_monan_choose,img_monan_menu;
        TextView textView_monan_tenMon,textView_monan_giaMon;
        RelativeLayout view;
        public MonAnViewHolder(View itemView) {
            super(itemView);
             img_monan_hinhanhMon = (ImageView) itemView.findViewById(R.id.img_cardview_HinhAnhMon);
             img_monan_choose = (ImageView) itemView.findViewById(R.id.img_cardview_choose);
             img_monan_menu = (ImageView) itemView.findViewById(R.id.img_cardview_menu);
             textView_monan_tenMon = (TextView) itemView.findViewById(R.id.textview_cardview_tenMon);
             textView_monan_giaMon = (TextView) itemView.findViewById(R.id.textview_cardview_giaMon);
            view = (RelativeLayout) itemView.findViewById(R.id.selected_overlay);
        }
    }
}
