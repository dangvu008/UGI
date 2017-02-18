package dang.ugi.com.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.MonAn;

/**
 * Created by DANG on 11/18/2016.
 */

public class MonAnSearchAdapter extends ArrayAdapter<MonAn> {
    Context context;
    List<MonAn> listMonAn;

    public MonAnSearchAdapter(Context context, int resource, List<MonAn> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listMonAn = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getConvertView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getConvertView(position,convertView,parent);
    }

    public View getConvertView(int position, View convertView, ViewGroup parent) {
        MonAnAutocompleteViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new MonAnAutocompleteViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_autocompletetextview_item,parent,false);
            viewHolder.textViewMa = (TextView) convertView.findViewById(R.id.textView_item_autocomplete_ma);
            viewHolder.textViewTenMon = (TextView) convertView.findViewById(R.id.textView_item_autocomplete_ten);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (MonAnAutocompleteViewHolder) convertView.getTag();
        }
        MonAn monAn= listMonAn.get(position);
        viewHolder.textViewTenMon.setText(monAn.getTenMonAn());
        viewHolder.textViewMa.setText(String.valueOf(monAn.getMaMonAn()));
        return convertView;
    }
    public class MonAnAutocompleteViewHolder{
        TextView textViewTenMon,textViewMa;
    }
}
