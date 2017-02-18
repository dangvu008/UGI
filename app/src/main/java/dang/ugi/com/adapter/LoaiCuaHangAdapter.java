package dang.ugi.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.LoaiCuaHang;

/**
 * Created by DANG on 10/29/2016.
 */

public class LoaiCuaHangAdapter extends BaseAdapter {
    List<LoaiCuaHang> list;
    Context context;

    public LoaiCuaHangAdapter(List<LoaiCuaHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getMaLoaiCuaHang();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
       return getCustomView(i,convertView,viewGroup);
    }
    public View getCustomView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        LoaiCuaHangViewHolder viewHolder;
        if (view==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custom_item_spinner_loaicuahang,parent,false);
            viewHolder = new LoaiCuaHangViewHolder();
            viewHolder.textViewLoaiCuaHang = (TextView) view.findViewById(R.id.textView_spinner_loaicuahang);
            view.setTag(viewHolder);
        }else{
            viewHolder = (LoaiCuaHangViewHolder) view.getTag();
        }
        LoaiCuaHang loaiCuaHang = list.get(position);
        //Log.d("tenloaicuahang",loaiCuaHang.getTenLoaiCuaHang());
        viewHolder.textViewLoaiCuaHang.setText(loaiCuaHang.getTenLoaiCuaHang());
        return view;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position,convertView,parent);
    }
    public class LoaiCuaHangViewHolder{
        TextView textViewLoaiCuaHang;

    }
}
