package com.highndry.hull.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.highndry.hull.R;

import org.androidannotations.annotations.EFragment;

/**
 * Created by ahmadalinasir on 2/21/15.
 *
 */

@EFragment
public class FragmentFour extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_four, container, false);




        return rootView;
    }
}