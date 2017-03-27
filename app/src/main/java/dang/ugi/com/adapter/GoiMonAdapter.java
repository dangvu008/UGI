package dang.ugi.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dang.ugi.com.asyntask.LoadGoiMonAsynTask;
import dang.ugi.com.R;
import dang.ugi.com.model.Entities.BanAn;
import dang.ugi.com.model.Entities.GoiMon;
import dang.ugi.com.model.Entities.NguoiDung;
import dang.ugi.com.model.Utils.FormatData;
import dang.ugi.com.model.Utils.PrefDangNhap;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.model.Utils.Static_Id;
import dang.ugi.com.presenter.BanAn.ImplPresenterBanAn;
import dang.ugi.com.presenter.GoiMon.ImplGoiMonPresenter;
import dang.ugi.com.view.GoiMon.GoiMonActivity;
import dang.ugi.com.view.GoiMon.ThanhToanDialog;

import static dang.ugi.com.model.Utils.Static_Id.REQUEST_CODE_THANHTOANGOIMON_TUGOIMON;

/**
 * Created by DANG on 11/20/2016.
 */

public class GoiMonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private Context context;
    private List<GoiMon> listGoiMon;
    private  GoiMon goiMonSelected ;
    private ImplPresenterBanAn implPresenterBanAn;
    private ImplGoiMonPresenter implGoiMonPresenter;
   private NguoiDung nguoiDung;
   private int maNhaHang;
    private BanAn banAn;
    View viewGoiMon;

    public GoiMonAdapter(List<GoiMon> listGoiMon, Context context) {
        this.listGoiMon = listGoiMon;
        this.context = context;
        if (PrefDangNhap.layNguoiDungHienTai(context)!=null)
            nguoiDung = PrefDangNhap.layNguoiDungHienTai(context);
        if (PrefNhaHang.layCuaHangHienTai(context)!=null)
              maNhaHang = PrefNhaHang.layCuaHangHienTai(context).getMaCuaHang();
        implPresenterBanAn = new ImplPresenterBanAn(context);
        implGoiMonPresenter = new ImplGoiMonPresenter(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_recyclerview_list_goimon, parent, false);
        viewGoiMon =  LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_goi_mon, parent, false);
        return new GoiMonViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GoiMonViewHolder viewHolder = (GoiMonViewHolder) holder;
        final GoiMon  mGoiMon = listGoiMon.get(position);
         banAn = implPresenterBanAn.layBanAnbyMaBanAn(mGoiMon.getMaBan());
        String tenBan=  banAn.getTenBanAn();
        String tenNguoiDung = nguoiDung.getTenNguoiDung();
        float tongTien = mGoiMon.getTongTien();
        String strTongTien = FormatData.formatMoneyVietNam(tongTien) +" VND";
        int tinhtrang = mGoiMon.getTinhTrang();

        viewHolder.textView_goimon_TenBan.setText(tenBan);
        viewHolder.textView_goimon_tongTien.setText(strTongTien);
        if (tinhtrang==-1){
            viewHolder.imageView_goiMon_tinhtrang.setImageResource(R.drawable.ic_close);
            viewHolder.textView_goimon_titleNhanVien.setText("Nhân viên Lập : ");
        }else if (tinhtrang==0){
            viewHolder.imageView_goiMon_tinhtrang.setImageResource(R.drawable.ic_priority_high);
            viewHolder.textView_goimon_titleNhanVien.setText("Nhân viên Lập : ");
        }else{
            viewHolder.imageView_goiMon_tinhtrang.setImageResource(R.drawable.ic_check);
            viewHolder.textView_goimon_titleNhanVien.setText("Nhân viên Thanh Toán : ");
        }
        viewHolder.textView_goimon_nhanVien.setText(tenNguoiDung);
        if (nguoiDung.getMaQuyen()!=1){
            viewHolder.imageView_goimon_menu.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.imageView_goimon_menu.setVisibility(View.VISIBLE);
        }
        viewHolder.card_view_item_goimon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGoiMon.getTinhTrang()==0){
                    goiMonSelected = mGoiMon;
                    showPopupMenu(view);
                }
            }
        });
        viewHolder.imageView_goimon_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenuTrai(view);
            }
        });


    }
    private void showPopupMenuTrai(View view){
        PopupMenu popupMenu = new PopupMenu(context,view);
        popupMenu.inflate(R.menu.menu_cardview_banan);
        popupMenu.setGravity(Gravity.CENTER_VERTICAL);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_menu_xoa:{
                        xoaGoiMon();
                    }break;
                    case R.id.item_menu_sua:{
                        suaGoiMon();
                    }
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void suaGoiMon() {
       chuyenManHinh(Static_Id.REQUEST_CODE_SUAGOIMON_TUGOIMON);
    }

    private void xoaGoiMon() {
        if (implGoiMonPresenter.xoaGoiMon(goiMonSelected.getMaGoiMon())!=-1){
            new LoadGoiMonAsynTask(context,viewGoiMon).execute(1);
        }
    }

    private void showPopupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(context,view);
        popupMenu.inflate(R.menu.menu_cardview_goimon);
        popupMenu.setGravity(Gravity.CENTER_VERTICAL);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_menu_list_goimon_chitiet:{
                        chuyenManHinhChiTietGoiMon();
                    }break;
                    case R.id.item_menu_list_goimon_chuyenBan:{
                        chuyenManHinhChuyenBan();
                    }break;
                    case R.id.item_menu_list_goimon_goimon :{
                        chuyenManHinhGoiMon();
                    }break;
                    case R.id.item_menu_list_goimon_thanhtoan:{
                        chuyenManHinhThanhToan();
                    }break;
                    case R.id.item_menu_list_goimon_huy:{
                        huyGoiMon();
                    }break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
    private void huyGoiMon() {
        goiMonSelected.setTinhTrang(-1);
        if (implGoiMonPresenter.capNhatGoiMon(goiMonSelected)!=-1){
            new LoadGoiMonAsynTask(context,viewGoiMon).execute(2);
        }
    }

    private void chuyenManHinhThanhToan() {
        Intent intent = new Intent((AppCompatActivity) context, ThanhToanDialog.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("GOIMON", goiMonSelected);
        intent.putExtra("bsGoiMon", bundle);
        intent.putExtra("requestcode",REQUEST_CODE_THANHTOANGOIMON_TUGOIMON);
        ((AppCompatActivity) context).startActivityForResult(intent, REQUEST_CODE_THANHTOANGOIMON_TUGOIMON);

    }
    private void chuyenManHinh(int requestcode){
        Intent intent = new Intent((AppCompatActivity) context, GoiMonActivity.class);
        Bundle bundle = new Bundle();
        int magoimon = goiMonSelected.getMaGoiMon();
        bundle.putSerializable("GOIMON", goiMonSelected);
        intent.putExtra("bsGoiMon", bundle);
        intent.putExtra("requestcode",requestcode);
        ((AppCompatActivity) context).startActivityForResult(intent, requestcode);
    }
    private void chuyenManHinhChiTietGoiMon() {
        chuyenManHinh(Static_Id.REQUEST_CODE_CHITIETGOIMON_TUGOIMON);
    }

    private void chuyenManHinhChuyenBan() {
    }

    private void chuyenManHinhGoiMon() {
        chuyenManHinh(Static_Id.REQUEST_CODE_GOIMONMOI_TUGOIMON);
    }
    @Override
    public int getItemCount() {
        return listGoiMon != null ? listGoiMon.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return listGoiMon.get(position).getMaGoiMon();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }

    public class GoiMonViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_goimon_TenBan, textView_goimon_tongTien, textView_goimon_nhanVien, textView_goimon_titleNhanVien;
        ImageView imageView_goimon_menu, imageView_goiMon_tinhtrang;
        CardView card_view_item_goimon;
        public GoiMonViewHolder(View itemView) {
            super(itemView);
            textView_goimon_TenBan = (TextView) itemView.findViewById(R.id.textView_list_goimon_TenBan);
            textView_goimon_tongTien = (TextView) itemView.findViewById(R.id.textView_list_goimon_tongtien);
            textView_goimon_titleNhanVien = (TextView) itemView.findViewById(R.id.textView_list_goimon_titleNhanVien);
            textView_goimon_nhanVien = (TextView) itemView.findViewById(R.id.textView_list_goimon_nhanvien);
            imageView_goimon_menu = (ImageView) itemView.findViewById(R.id.imageView_list_goimon_menu);
            imageView_goiMon_tinhtrang = (ImageView) itemView.findViewById(R.id.imageView_list_goimon_tinhtrang);
            card_view_item_goimon = (CardView) itemView.findViewById(R.id.card_view_item_goimon);

        }
    }
}

