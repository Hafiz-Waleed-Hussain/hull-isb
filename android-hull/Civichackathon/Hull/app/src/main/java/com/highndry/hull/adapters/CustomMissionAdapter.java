package com.highndry.hull.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.highndry.hull.MissionModel;
import com.highndry.hull.R;
import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.res.StringArrayRes;

import java.util.List;
import java.util.Random;

/**
 * Created by ahmadalinasir on 2/22/15.
 */
public class CustomMissionAdapter extends ArrayAdapter<ParseObject> {

    Context context;
    List<ParseObject> items;
    int resourceId;
    boolean sub_status;
    Random random;


    public CustomMissionAdapter(Context context, int resourceId, List<ParseObject> items) {
        super(context, resourceId, items);
        this.context = context;
        this.items = items;
        this.resourceId = resourceId;
        random = new Random();

    }

    public CustomMissionAdapter(Context context, int resourceId, List<ParseObject> items,boolean sub_status) {
        super(context, resourceId, items);
        this.context = context;
        this.items = items;
        this.resourceId = resourceId;
        this.sub_status = sub_status;

    }

    @Override
    public ParseObject getItem(int position) {

        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v = convertView;
        MissionViewHolder holder;
        if( v == null){

            LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(resourceId, null);

            holder = new MissionViewHolder();
            assert v != null;

            holder.missionBackground = (LinearLayout) v.findViewById(R.id.missionLL);
            //holder.missionImage = (ImageView) v.findViewById(R.id.missionImage);
            holder.missionRSPV = (TextView) v.findViewById(R.id.missionRSPV);
            holder.missionDesc = (TextView) v.findViewById(R.id.missionDesc);
            holder.location = (TextView) v.findViewById(R.id.location);


            v.setTag(holder);
        }else {
            holder = (MissionViewHolder) v.getTag();
        }

        if(position % 2==0)
        {
            holder.missionBackground.setBackgroundColor(context.getResources().getColor(R.color.item_one));
            holder.missionDesc.setTextColor(context.getResources().getColor(R.color.item_one_text));
            holder.missionRSPV.setTextColor(context.getResources().getColor(R.color.item_one_text));
            holder.location.setTextColor(context.getResources().getColor(R.color.item_one_text));
            // even position color
        }
        else
        {
            holder.missionBackground.setBackgroundColor(context.getResources().getColor(R.color.item_two));
            holder.missionDesc.setTextColor(context.getResources().getColor(R.color.item_two_text));
            holder.missionRSPV.setTextColor(context.getResources().getColor(R.color.item_two_text));
            holder.location.setTextColor(context.getResources().getColor(R.color.item_two_text));
            // odd position color
        }
        //holder.missionTitle.setText(items.get(position).getString("title"));
        holder.missionDesc.setText(items.get(position).getString("title"));
        int randomNumber = random.nextInt(99 - 25 + 1) + 25;
        holder.missionRSPV.setText(String.valueOf(randomNumber));
        /*holder.contentTitle.setText(items.get(position).getTitle());
        String thumb_url = items.get(position).getThumb();
        final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
        String urlEncoded = Uri.encode(thumb_url, ALLOWED_URI_CHARS);

        try{
            if(urlEncoded != null && urlEncoded.length() > 0){
                Picasso.with(context).load(urlEncoded)
                        .placeholder(R.drawable.ic_movie_icon)
                        .error(R.drawable.ic_movie_icon)
                        .into(holder.contentImage);
            }
        }catch (Exception e){
            Log.d("invalid_thumb_url", e.toString());
        }*/


/*
        if(sub_status){
            holder.contentPrice.setVisibility(View.INVISIBLE);
        }else {
            holder.contentPrice.setVisibility(View.VISIBLE);
            holder.contentPrice.setText("Price: "+String.valueOf(items.get(position).getPrice()));

        }*/


        return v;

    }

    public class MissionViewHolder {

        public LinearLayout missionBackground = null;
        public ImageView missionImage = null;
        public TextView missionTitle = null;
        public TextView missionRSPV = null;
        public TextView missionDesc = null;
        public TextView location = null;


        public MissionViewHolder() {
        }
    }



}
