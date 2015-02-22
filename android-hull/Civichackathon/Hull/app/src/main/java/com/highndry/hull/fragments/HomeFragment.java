package com.highndry.hull.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.highndry.hull.R;
import com.highndry.hull.adapters.HomeFragmentPagerAdapter;

import org.androidannotations.annotations.EFragment;

/**
 * Created by ahmadalinasir on 1/22/15.
 *
 */

@EFragment
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;
    private PagerSlidingTabStrip tabs;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mViewPager = (ViewPager) rootView.findViewById(R.id.home_view_pager);
        tabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.home_pager_header);
        tabs.setShouldExpand(true);

        configureViewPager();


        return rootView;

    }

    private void configureViewPager() {

        HomeFragmentPagerAdapter viewPagerAdapter = new HomeFragmentPagerAdapter(getActivity(), getActivity().getSupportFragmentManager());

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources()
                .getDisplayMetrics());
        mViewPager.setPageMargin(pageMargin);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(0);
        tabs.setViewPager(mViewPager);
        tabs.setIndicatorColor(getResources().getColor(R.color.tab_select_color));
        tabs.setTextColor(getResources().getColor(R.color.white));

    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        getActivity().supportInvalidateOptionsMenu();
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        getActivity().supportInvalidateOptionsMenu();
    }
}
