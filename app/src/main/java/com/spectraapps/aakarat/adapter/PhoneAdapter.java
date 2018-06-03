package com.spectraapps.aakarat.adapter;

/**
 * Created by MahmoudAyman on 03/06/2018.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spectraapps.aakarat.R;
import com.spectraapps.aakarat.model.PhoneSpinnerModel;

import java.util.List;

public class PhoneAdapter extends ArrayAdapter<PhoneSpinnerModel> {

    public PhoneAdapter(Context context, int resouceId, int textviewId, List<PhoneSpinnerModel> list) {

        super(context, resouceId, textviewId, list);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return rowview(convertView, position);
    }

    private View rowview(View convertView, int position) {

        PhoneSpinnerModel rowItem = getItem(position);

        viewHolder holder;
        View rowview = convertView;
        if (rowview == null) {

            holder = new viewHolder();
            LayoutInflater flater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowview = flater.inflate(R.layout.single_item_spinner_phone_countries, null, false);

            holder.txtTitle = rowview.findViewById(R.id.textView);
            holder.imageView = rowview.findViewById(R.id.imageView);
            rowview.setTag(holder);
        } else {
            holder = (viewHolder) rowview.getTag();
        }
        holder.imageView.setImageResource(rowItem.getImageId());
        holder.txtTitle.setText(rowItem.getText());

        return rowview;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return rowview(convertView, position);
    }

    private static class viewHolder {
        TextView txtTitle;
        ImageView imageView;
    }

}