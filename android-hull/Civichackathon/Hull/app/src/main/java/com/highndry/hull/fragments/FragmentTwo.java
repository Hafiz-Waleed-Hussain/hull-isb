package com.highndry.hull.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.highndry.hull.R;
import com.highndry.hull.adapters.CustomMissionAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

import java.util.List;

/**
 * Created by ahmadalinasir on 2/21/15.
 */

@EFragment
public class FragmentTwo extends Fragment {

    CustomMissionAdapter customMissionAdapter;
    ListView missionList;
    private FrameLayout mFrameOverlay;

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