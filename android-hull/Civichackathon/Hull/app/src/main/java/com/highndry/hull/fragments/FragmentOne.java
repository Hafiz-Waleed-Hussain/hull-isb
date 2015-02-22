package com.highndry.hull.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;
import com.highndry.hull.MissionDetailActivity_;
import com.highndry.hull.R;
import com.highndry.hull.adapters.CustomMissionAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmadalinasir on 2/21/15.
 *
 */

@EFragment
public class FragmentOne extends Fragment {

    CustomMissionAdapter customMissionAdapter;
    ListView missionList;
    private FrameLayout mFrameOverlay;

    List<ParseObject> missionsCopy;

    //ParseObject parseObject;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_one, container, false);

        //customMissionAdapter = new CustomMissionAdapter()
        missionList = (ListView) rootView.findViewById(R.id.missionList);
        mFrameOverlay = (FrameLayout) rootView.findViewById(R.id.overlay);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Mission");
        setProgressDialog(true);
        query.findInBackground(new FindCallback<ParseObject>() {

            public void done(List<ParseObject> missions, ParseException e) {
                if (e == null) {
                    setProgressDialog(false);
                    missionsCopy = new ArrayList<ParseObject>();
                    missionsCopy = missions;
                    customMissionAdapter = new CustomMissionAdapter(getActivity(),R.layout.mission_item,missions);
                    missionList.setAdapter(customMissionAdapter);
                    Log.d("missions", "Retrieved " + missions.size() + " missions");
                } else {
                    //Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        /*Bundle bundle = new Bundle();
        bundle.putString("fields", "picture.type(large),quotes,email,first_name,last_name,username");*/

        //profilePicture = (CircularImageView) rootView.findViewById(R.id.profilePicture);

        //makeMeRequest();

        missionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), MissionDetailActivity_.class);
                intent.putExtra("title",missionsCopy.get(position).getString("title"));
                startActivity(intent);

            }
        });

        return rootView;
    }


    @UiThread
    void setProgressDialog(boolean progress){
        if (progress) {
            mFrameOverlay.setVisibility(View.VISIBLE);
        } else {
            mFrameOverlay.setVisibility(View.GONE);
        }
    }
}