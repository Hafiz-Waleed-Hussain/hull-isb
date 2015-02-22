package com.highndry.hull.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.highndry.hull.R;


import java.util.List;
import java.util.Random;

/**
 * Created by ahmadalinasir on 2/22/15.
 */
public class CustomChatAdapter extends ArrayAdapter<String> {

    Context context;
    List<String> items;
    int resourceId;
    boolean sub_status;
    //Random random;


    public CustomChatAdapter(Context context, int resourceId, List<String> items) {
        super(context, resourceId, items);
        this.context = context;
        this.items = items;
        this.resourceId = resourceId;
        //random = new Random();

    }

    public CustomChatAdapter(Context context, int resourceId, List<String> items,boolean sub_status) {
        super(context, resourceId, items);
        this.context = context;
        this.items = items;
        this.resourceId = resourceId;
        this.sub_status = sub_status;

    }

    @Override
    public String getItem(int position) {

        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v = convertView;
        /*MissionViewHolder holder;
        if( v == null){

            LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(resourceId, null);

            holder = new MissionViewHolder();
            assert v != null;

            holder.missionRSPV = (TextView) v.findViewById(R.id.missionRSPV);
            holder.missionDesc = (TextView) v.findViewById(R.id.missionDesc);


            v.setTag(holder);
        }else {
            holder = (MissionViewHolder) v.getTag();
        }

        holder.missionDesc.setTextColor(context.getResources().getColor(R.color.item_one_text));
*/


        return v;

    }

    public class CommentViewHolder {


        public TextView comment = null;


        public CommentViewHolder() {
        }
    }



}
