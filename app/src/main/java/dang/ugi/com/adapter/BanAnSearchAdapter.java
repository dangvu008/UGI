package dang.ugi.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.BanAn;

/**
 * Created by DANG on 11/12/2016.
 */

public class BanAnSearchAdapter extends ArrayAdapter<BanAn> {
    Context context;
    List<BanAn> listBanAn;
    int layoutId;
    boolean chuyenban = false;
    public BanAnSearchAdapter(Context context,int resourceId, List<BanAn> listBanAn) {
        super(context,0,listBanAn);
        this.context = context;
        this.listBanAn = listBanAn;
        this.layoutId =  resourceId;
    }



    @Override
    public int getCount() {
        return (listBanAn!=null ? listBanAn.size():0);
    }


    @Override
    public long getItemId(int i) {
        return listBanAn.get(i).getMaBanAn();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return show(i,view,viewGroup);
    }

    public View show(int position,View convertView,ViewGroup parent){
        View view1 = convertView;
        BanAnViewHolder viewHolder;
        if (view1==null){
            viewHolder = new BanAnViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view1= inflater.inflate(R.layout.custom_autocompletetextview_item,parent,false);
            viewHolder.textViewTenBan = (TextView) view1.findViewById(R.id.textView_item_autocomplete_ten);
            viewHolder.textViewMaBan = (TextView) view1.findViewById(R.id.textView_item_autocomplete_ma);
            view1.setTag(viewHolder);
        }else{
            viewHolder = (BanAnViewHolder) view1.getTag();
        }
        BanAn banAn = listBanAn.get(position);
        viewHolder.textViewTenBan.setText(banAn.getTenBanAn());
        viewHolder.textViewMaBan.setText(String.valueOf(banAn.getMaBanAn()));
        return view1;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return show(position,convertView,parent);
    }

    public class BanAnViewHolder{
        TextView textViewTenBan,textViewMaBan;
    }
}
