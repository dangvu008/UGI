package dang.ugi.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.LoaiMon;

/**
 * Created by DANG on 9/29/2016.
 */

public class LoaiMonAnAdapterSpinner extends BaseAdapter {
    Context context;
    int layoutId;
    List<LoaiMon> listLoaiMon;

    public LoaiMonAnAdapterSpinner(Context context, int layoutId, List<LoaiMon> listLoaiMon) {
        this.context = context;
        this.layoutId = layoutId;
        this.listLoaiMon = listLoaiMon;
    }

    @Override
    public int getCount() {
        return listLoaiMon==null ? 0 : listLoaiMon.size();
    }

    @Override
    public Object getItem(int i) {
        return listLoaiMon.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listLoaiMon.get(i).getMaLoaiMon();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        LoaiMonAnViewHolder viewHolder;
        if (view==null){
            viewHolder = new LoaiMonAnViewHolder();
          LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          view = inflater.inflate(layoutId,viewGroup,false);
            viewHolder.textViewTenLoaiMon = (TextView) view.findViewById(R.id.textViewTenLoaiMon);
            view.setTag(viewHolder);
        }else{
            viewHolder = (LoaiMonAnViewHolder) view.getTag();
        }
        LoaiMon loaiMon = listLoaiMon.get(position);
        viewHolder.textViewTenLoaiMon.setText(loaiMon.getTenLoaiMon());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LoaiMonAnViewHolder viewHolder;
        if (view==null){
            viewHolder = new LoaiMonAnViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutId,parent,false);
            viewHolder.textViewTenLoaiMon = (TextView) view.findViewById(R.id.textViewTenLoaiMon);
            view.setTag(viewHolder);
        }else{
            viewHolder = (LoaiMonAnViewHolder) view.getTag();
        }
        LoaiMon loaiMon = listLoaiMon.get(position);
        viewHolder.textViewTenLoaiMon.setText(loaiMon.getTenLoaiMon());
        return view;
    }
    class LoaiMonAnViewHolder{
        TextView textViewTenLoaiMon;

    }
}
