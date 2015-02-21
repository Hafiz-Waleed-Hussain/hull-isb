package com.highndry.hull.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.highndry.hull.fragments.FragmentFour;
import com.highndry.hull.fragments.FragmentFour_;
import com.highndry.hull.fragments.FragmentOne;
import com.highndry.hull.fragments.FragmentOne_;
import com.highndry.hull.fragments.FragmentThree;
import com.highndry.hull.fragments.FragmentThree_;
import com.highndry.hull.fragments.FragmentTwo;
import com.highndry.hull.fragments.FragmentTwo_;


/**
 * Created by AhmedAli on 2/25/14.
 *
 */
public class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter {
    Context mContext = null;
    final int PAGE_COUNT = 4;

    private FragmentOne fragmentOne = new FragmentOne_();
    private FragmentTwo fragmentTwo= new FragmentTwo_();
    private FragmentThree fragmentThree= new FragmentThree_();
    private FragmentFour fragmentFour= new FragmentFour_();


    private static final int TAB_INDEX_ONE = 0;
    private static final int TAB_INDEX_TWO = 1;
    private static final int TAB_INDEX_THREE = 2;
    private static final int TAB_INDEX_FOUR = 3;

    public HomeFragmentPagerAdapter(Context context, FragmentManager mgr) {
        super(mgr);
        this.mContext = context;
    }



    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case TAB_INDEX_ONE:
                return fragmentOne;
            case TAB_INDEX_TWO:
                return fragmentTwo;
            case TAB_INDEX_THREE:
                return fragmentThree;
            case TAB_INDEX_FOUR:
                return fragmentFour;

        }
        throw new IllegalStateException("No fragment at position " + arg0);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        String tabLabel = null;
        switch (position) {
            case TAB_INDEX_ONE:
                tabLabel = "Category 1";
                break;
            case TAB_INDEX_TWO:
                tabLabel = "Category 2";
                break;
            case TAB_INDEX_THREE:
                tabLabel = "Category 3";
                break;
            case TAB_INDEX_FOUR:
                tabLabel = "Category 4";
                break;

        }
        return tabLabel;
    }
}
