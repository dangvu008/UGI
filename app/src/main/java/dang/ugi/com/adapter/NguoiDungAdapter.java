package dang.ugi.com.adapter;

import android.content.Context;
import android.os.Build;
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

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.NguoiDung;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.NguoiDung.ImplNguoiDungPresenter;

/**
 * Created by DANG on 1/7/2017.
 */

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.NguoiDungViewHolder>{
    private Context context;
    private List<NguoiDung> list;
    private int maCuahang ;
    private ImplNguoiDungPresenter implNguoiDungPresenter;
    public NguoiDungAdapter(Context context,List<NguoiDung> list) {
        this.context = context;
        this.list = list;
        implNguoiDungPresenter = new ImplNguoiDungPresenter(context);
        maCuahang = PrefNhaHang.layCuaHangHienTai(context).getMaCuaHang();
    }

    @Override
    public NguoiDungViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_nguoidung,parent,false);
        return new NguoiDungViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NguoiDungViewHolder holder, int position) {
         final NguoiDung nguoiDung = list.get(position);
        String tenNguoiDung = nguoiDung.getTenNguoiDung();
        int trangthai = nguoiDung.getTinhTrang();
        int chucvu = nguoiDung.getMaQuyen();
        holder.textView_tenNguoiDung.setText(tenNguoiDung);
        if (trangthai==0){
            holder.textView_trangthai.setText("Offline");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.textView_trangthai.setTextColor(context.getColor(R.color.color_button_thoat));
            }else{
                holder.textView_trangthai.setTextColor(context.getResources().getColor(R.color.color_button_thoat));
            }
        }else if (trangthai==1){
            holder.textView_trangthai.setText("Online");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.textView_trangthai.setTextColor(context.getColor(R.color.light_green_600));
            }else{
                holder.textView_trangthai.setTextColor(context.getResources().getColor(R.color.light_green_600));
            }
        }
        if (chucvu==1){
            holder.textView_ChucVu.setText(R.string.quanly);
        }else if (chucvu==2){
            holder.textView_ChucVu.setText(R.string.nhanvien);
        }
        holder.img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenuTrai(view,nguoiDung.getMaNguoiDung());
            }
        });
    }

    private void showMenuTrai(View view, final int maNguoiDung) {
        PopupMenu popupMenu = new PopupMenu(context,view);
        popupMenu.inflate(R.menu.menu_cardview_banan);
        popupMenu.setGravity(Gravity.CENTER_HORIZONTAL);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_menu_sua:{
                        suaNguoiDung(maNguoiDung);
                    }break;
                    case R.id.item_menu_xoa:{
                        xoaNguoiDung(maNguoiDung);
                    }break;
                }
                return false;
            }
        });
    }

    private void xoaNguoiDung(int maNguoiDung) {
    }

    private void suaNguoiDung(int maNguoiDung) {
    }

    @Override
    public int getItemCount() {
        return (list!=null?list.size():0);
    }

    class NguoiDungViewHolder extends RecyclerView.ViewHolder{
        private TextView textView_tenNguoiDung,textView_ChucVu,textView_trangthai;
        private ImageView img_menu;
        public NguoiDungViewHolder(View itemView) {
            super(itemView);
            textView_tenNguoiDung = (TextView) itemView.findViewById(R.id.textView_list_nguoidung_ten);
            textView_ChucVu = (TextView) itemView.findViewById(R.id.textView_list_NguoiDung_ChucVu);
            textView_trangthai = (TextView) itemView.findViewById(R.id.textview_item_nguoidung_trangthai);
            img_menu = (ImageView) itemView.findViewById(R.id.imageView_list_nguoidung_menu);

        }
    }
}
