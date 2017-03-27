package dang.ugi.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.asyntask.LoadBanAsynTask;
import dang.ugi.com.asyntask.LoadGoiMonAsynTask;
import dang.ugi.com.model.Entities.BanAn;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.GoiMon;
import dang.ugi.com.model.Entities.NguoiDung;
import dang.ugi.com.model.Utils.PrefDangNhap;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.model.Utils.Static_Id;
import dang.ugi.com.presenter.BanAn.ImplPresenterBanAn;
import dang.ugi.com.presenter.ChiTietGoiMon.ImplChiTietGoiMonPresenter;
import dang.ugi.com.presenter.GoiMon.ImplGoiMonPresenter;
import dang.ugi.com.view.BanAn.ThemBanActivity;
import dang.ugi.com.view.GoiMon.ChuyenBanActivity;
import dang.ugi.com.view.GoiMon.GoiMonActivity;
import dang.ugi.com.view.GoiMon.ThanhToanDialog;

/**
 * Created by DANG on 9/18/2016.
 */
public class BanAnAdapter extends RecyclerView.Adapter<BanAnAdapter.BanAnItemViewHolder> {

    private Context _context;
    private List<BanAn> _listBanAn = new ArrayList<>();
    View viewItem, viewFooter, viewFragment;
    //BanAn  banAn;
    private ImplPresenterBanAn implPresenterBanAn;
    private ImplChiTietGoiMonPresenter chiTietGoiMonPresenter;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_FOOTER = 2;
    private ImplGoiMonPresenter implGoiMonPresenter;
    private GoiMon mGoimon;
    private BanAn banAnSelected;
    private int maCuaHang, maNguoiDung, maBan;
    private BanAnItemViewHolder viewHolder;
    //private final PublishSubject<String> onClickSubject = PublishSubject.create();
    public BanAnAdapter(Context context, List<BanAn> listBanAn) {
        this._context = context;
        this._listBanAn = listBanAn;
        implPresenterBanAn = new ImplPresenterBanAn(context);
        implGoiMonPresenter = new ImplGoiMonPresenter(context);
        chiTietGoiMonPresenter = new ImplChiTietGoiMonPresenter(context);
        CuaHang cuaHangHT =PrefNhaHang.layCuaHangHienTai(context);
        NguoiDung nguoiDung = PrefDangNhap.layNguoiDungHienTai(context);
        if (cuaHangHT!=null)
            maCuaHang = cuaHangHT.getMaCuaHang();
        if (nguoiDung!=null)
            maNguoiDung = nguoiDung.getMaNguoiDung();
    }

    @Override
    public BanAnItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cusom_cardview_banan, parent, false);
        viewFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_banan, parent, false);
        _context = parent.getContext();
        return new BanAnItemViewHolder(viewItem);

    /*   else if (viewType==TYPE_FOOTER){
           View viewFooter = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_footer_recyclerview_banan,parent,false);
            viewFooter.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return new BanAnFooterViewHolder(viewFooter);
        }*/

    }



    @Override
    public int getItemViewType(int position) {
        if (position >= (getItemCount() + 1)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;

    }

    @Override
    public void onBindViewHolder(BanAnItemViewHolder holder, int position) {
        viewHolder = holder;
        banAnSelected = _listBanAn.get(position);
        viewHolder.textViewTenBan.setText(banAnSelected.getTenBanAn());
        final int maBanSelect = banAnSelected.getMaBanAn();
        String tenBan = banAnSelected.getTenBanAn();
        int tinhTrang = banAnSelected.getTinhTrang();
        viewHolder.textViewMaBan.setText(String.valueOf(maBanSelect));
        if (tinhTrang == 1) {
            // banAnItemViewHolder.relativeLayout.setBackgroundColor(_context.getResources().getColor(R.color.green_200));
            viewHolder.imageViewAnhBanAn.setImageResource(R.drawable.ban_an_true);

        } else {
            viewHolder.imageViewAnhBanAn.setImageResource(R.drawable.ban_an_false);
        }
        if (PrefDangNhap.layNguoiDungHienTai(_context).getMaQuyen() == 1) {
            viewHolder.imageViewoverflow.setVisibility(View.VISIBLE);
        }
        viewHolder.imageViewoverflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(viewHolder.imageViewoverflow,maBanSelect);
            }
        });

        viewHolder.imageViewAnhBanAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenuClick(view,maBanSelect);
            }
        });

    }/*if (holder instanceof BanAnFooterViewHolder){
            final BanAnFooterViewHolder banAnFooterViewHolder = (BanAnFooterViewHolder) holder;
            banAnFooterViewHolder.btnThemBanMoi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.showPopupMenutThemBan(banAnFooterViewHolder.btnThemBanMoi);
                }
            });
        }*/


    private void showPopupMenu(View view, final int maBanG) {
        PopupMenu popupMenu = new PopupMenu(_context, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_cardview_banan, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                                 @Override
                                                 public boolean onMenuItemClick(MenuItem item) {
                                                     switch (item.getItemId()) {
                                                         case R.id.item_menu_sua: {
                                                             suaBanAn(maBanG);
                                                         }
                                                         break;
                                                         case R.id.item_menu_xoa: {
                                                             xoaBanAn(maBanG);
                                                         }
                                                         break;
                                                     }
                                                     return false;
                                                 }
                                             }
        );
        popupMenu.show();
    }

    private void showPopupMenuClick(View view,final int maBanG) {
        PopupMenu popupMenu = new PopupMenu(_context, view);
        popupMenu.inflate(R.menu.menu_cardview_goimon);
        popupMenu.setGravity(Gravity.CENTER_VERTICAL);
        Menu menu = popupMenu.getMenu();
        MenuItem menuItemChiTiet = menu.findItem(R.id.item_menu_list_goimon_chitiet);
        MenuItem menuItemChuyenBan = menu.findItem(R.id.item_menu_list_goimon_chuyenBan);
        MenuItem menuItemThanhToan = menu.findItem(R.id.item_menu_list_goimon_thanhtoan);
        MenuItem menuItemHuy = menu.findItem(R.id.item_menu_list_goimon_huy);
        banAnSelected = implPresenterBanAn.layBanAnbyMaBanAn(maBanG);
        int tinhtrang = banAnSelected.getTinhTrang();
        if (tinhtrang == 0 || tinhtrang == -1) {
            menuItemChiTiet.setVisible(false);
            menuItemChuyenBan.setVisible(false);
            menuItemThanhToan.setVisible(false);
            menuItemHuy.setVisible(false);
        } else {
            menuItemChiTiet.setVisible(true);
            menuItemChuyenBan.setVisible(true);
            menuItemThanhToan.setVisible(true);
            menuItemHuy.setVisible(true);
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_menu_list_goimon_chitiet: {
                        chuyenManHinhChiTietGoiMon(maBanG);
                    }
                    break;
                    case R.id.item_menu_list_goimon_chuyenBan: {
                        chuyenManHinhChuyenBan(maBanG);
                    }
                    break;
                    case R.id.item_menu_list_goimon_goimon: {
                        chuyenManHinhGoiMon(maBanG);
                    }
                    break;
                    case R.id.item_menu_list_goimon_thanhtoan: {
                        chuyenManHinhThanhToan(maBanG);
                    }
                    break;
                    case R.id.item_menu_list_goimon_huy: {
                        huyGoiMon(maBanG);
                    }
                    break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void huyGoiMon(int maban) {
        mGoimon = implGoiMonPresenter.layGoiMonDangGoi(maban);
        mGoimon.setTinhTrang(-1);
        if (implGoiMonPresenter.capNhatGoiMon(mGoimon) != -1) {
            new LoadGoiMonAsynTask(_context, viewFragment).execute(2);
        }
    }

    private void chuyenManHinhThanhToan(int maban) {
        mGoimon = implGoiMonPresenter.layGoiMonDangGoi(maban);
        if (chiTietGoiMonPresenter.listChiTietGoiMon(mGoimon.getMaGoiMon()).size()!=0){
            mGoimon = implGoiMonPresenter.layGoiMonDangGoi(maBan);
            Intent intent = new Intent((AppCompatActivity) _context, ThanhToanDialog.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("GOIMON", mGoimon);
            intent.putExtra("bsGoiMon", bundle);
            intent.putExtra("requestcode", Static_Id.REQUEST_CODE_THANHTOANGOIMON_TUBAN);
            ((AppCompatActivity) _context).startActivityForResult(intent, Static_Id.REQUEST_CODE_THANHTOANGOIMON_TUBAN);
        }else{
            Toast.makeText(_context, "ban chua goi mon", Toast.LENGTH_SHORT).show();
        }
      
    }

    private void chuyenManHinh(int requestcode,int maBanGoi) {
        Intent intent = new Intent( _context, GoiMonActivity.class);
        intent.putExtra("requestcode", requestcode);
        intent.putExtra("maBan", maBanGoi);
        ((AppCompatActivity) _context).startActivityForResult(intent, requestcode);
    }

    private void chuyenManHinhChiTietGoiMon(int maBan) {
        chuyenManHinh(Static_Id.REQUEST_CODE_CHITIETGOIMON_TUBAN, maBan);
    }

    private void chuyenManHinhChuyenBan(int maGoimon) {
        Intent intent = new Intent(_context, ChuyenBanActivity.class);
        Bundle bundle = new Bundle();
        GoiMon gm = implGoiMonPresenter.layGoiMonTheoMaGoiMon(maGoimon);
        bundle.putSerializable("GOIMON", gm);
        intent.putExtra("bsGoiMon", bundle);
        intent.putExtra("requestcode", Static_Id.REQUEST_CODE_CHUYENBAN_TUBAN);
        ((AppCompatActivity) _context).startActivityForResult(intent, Static_Id.REQUEST_CODE_CHUYENBAN_TUBAN);
    }

    private void chuyenManHinhGoiMon(int maBanGoi) {
        String tenBan = implPresenterBanAn.layBanAnbyMaBanAn(maBanGoi).getTenBanAn();
        chuyenManHinh(Static_Id.REQUEST_CODE_GOIMONMOI_TUBAN,maBanGoi);
    }

    @Override
    public int getItemCount() {
        return (_listBanAn == null ? 0 : _listBanAn.size());
    }

    public class BanAnItemViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView textViewTenBan, textViewMaBan;
        ImageView imageViewAnhBanAn, imageViewoverflow;

        BanAnItemViewHolder(View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_cardview_banAn);
            textViewTenBan = (TextView) itemView.findViewById(R.id.textviewTenBan);
            imageViewAnhBanAn = (ImageView) itemView.findViewById(R.id.imageViewAnhBAnAn);
            imageViewoverflow = (ImageView) itemView.findViewById(R.id.overflow);
            textViewMaBan = (TextView) itemView.findViewById(R.id.textViewMaBan);

        }

    }
/*
    public class BanAnFooterViewHolder extends RecyclerView.ViewHolder{
        Button btnThemBanMoi;
        public BanAnFooterViewHolder(View itemView) {
            super(itemView);
            btnThemBanMoi = (Button) itemView.findViewById(R.id.btn_themBan_ThemBanAn);
        }
*/


    private void suaBanAn(int maBan) {
        Intent intent = new Intent(_context, ThemBanActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("BanAnEdit", banAnSelected);
        intent.putExtra("bBanAn", bundle);
        ((AppCompatActivity) _context).startActivityForResult(intent, Static_Id.REQUEST_CODE_SUABAN);
    }

    private void xoaBanAn(int maBan) {
        if (implPresenterBanAn.xoaBanAn(maBan) != -1) {
            notifyDataSetChanged();
            new LoadBanAsynTask(_context, viewFragment).execute("");
        }
    }

}
