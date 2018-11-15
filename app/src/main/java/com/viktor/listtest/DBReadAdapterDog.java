package com.viktor.listtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.viktor.listtest.DBModels.Dog;

import java.util.ArrayList;

public class DBReadAdapterDog extends ArrayAdapter<Dog> {

    public DBReadAdapterDog(Context context, ArrayList<Dog> items){
        super(context, R.layout.list_item_row, items);
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent){
        ViewHolder holder;

        if(rowView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.list_item_row, parent, false);

            holder = new ViewHolder();
            holder.name_row = rowView.findViewById(R.id.name_row);
            holder.weight_row = rowView.findViewById(R.id.weight_row);

            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        holder.item = getItem(position);

        holder.name_row.setText(holder.item.getDog_name());
        holder.weight_row.setText(String.valueOf(holder.item.getDog_idealweight()));

        return rowView;
    }

    class ViewHolder {
        Dog item;
        TextView name_row;
        TextView weight_row;
    }
}
