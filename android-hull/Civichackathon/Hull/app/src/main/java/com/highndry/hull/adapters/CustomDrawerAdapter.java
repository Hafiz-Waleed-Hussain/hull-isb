package com.highndry.hull.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.highndry.hull.R;


/**
 * Created by ahmadalinasir on 1/20/15.
 *
 */

public class CustomDrawerAdapter extends ArrayAdapter {

    String[] features;
    Context context;
    TypedArray feature_icons;


    @SuppressWarnings("unchecked")
    public CustomDrawerAdapter(Context context, int resource, String[] features, TypedArray feature_icons) {
        super(context, resource, features);
        this.context = context;
        this.features = features;
        this.feature_icons = feature_icons;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.custom_drawer_item, null);

            DrawerHolder holder = new DrawerHolder();
            holder.feature = (TextView)v.findViewById(R.id.feature);
            holder.feature_icon = (ImageView) v.findViewById(R.id.feature_icon);


            v.setTag(holder);
        }

        //set Images in Views
        DrawerHolder holder = (DrawerHolder)v.getTag();
        holder.feature.setText(features[position]);
        holder.feature_icon.setImageResource(feature_icons.getResourceId(position, -1));



        return v;
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }


    //Holder
    private class DrawerHolder {

        TextView feature = null;
        ImageView feature_icon = null;

        private DrawerHolder() {
        }
    }
}
