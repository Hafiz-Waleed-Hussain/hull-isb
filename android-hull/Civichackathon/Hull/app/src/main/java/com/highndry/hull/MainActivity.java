package com.highndry.hull;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.highndry.hull.adapters.CustomDrawerAdapter;
import com.parse.ParseFacebookUtils;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EActivity;


public class MainActivity extends ActionBarActivity  {

    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private TextView mTitle;
    private CircularImageView profilePicture;

    ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView mListView;
    private RelativeLayout mDrawerRelativeLayout;
    private String[] mFeatureTitles;
    private TypedArray mFeatureIcons;
    private String[] fragments;

    //private CharSequence mDrawerTitle;
    //private CharSequence mToolBarTitle;



    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */

    //private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private int mCurrentSelectedPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerRelativeLayout = (RelativeLayout) findViewById(R.id.left_drawer_relative);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mListView = (ListView)findViewById(R.id.left_drawer);
        //mTitle = (TextView) mToolBar.findViewById(R.id.toolbar_title);
        profilePicture = (CircularImageView) mToolBar.findViewById(R.id.profilePicture);

        //mToolBarTitle = mDrawerTitle = mTitle.getText().toString();

        mFeatureTitles = getResources().getStringArray(R.array.feature_array);
        mFeatureIcons = getResources().obtainTypedArray(R.array.feature_icons);
        fragments = getResources().getStringArray(R.array.fragments);

        //mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.orange_700));
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolBar,
                R.string.drawer_open,
                R.string.drawer_close
        )

        {
            public void onDrawerClosed(View view)
            {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
                syncState();
                //mTitle.setText(mToolBarTitle);
            }

            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
                //mTitle.setText(mDrawerTitle);
            }
        };



        selectItem(mCurrentSelectedPosition);
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);

        mListView.setAdapter(new CustomDrawerAdapter(this,R.layout.custom_drawer_item,mFeatureTitles,mFeatureIcons));
        mListView.setOnItemClickListener(new DrawerItemClickListener());

        if (mToolBar != null) {
            setSupportActionBar(mToolBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayOptions(getSupportActionBar().getDisplayOptions() ^ ActionBar.DISPLAY_SHOW_TITLE);


        makeMeRequest();


    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        //boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerRelativeLayout);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
        // Handle action buttons
        /*switch(item.getItemId()) {
            case R.id.action_websearch:
                // blah bluh bleh

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/
    }


    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(final int position) {

        // update the main content by replacing fragments
       /* if(position == 0 || position == 3){

            Bundle args = new Bundle();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, Fragment.instantiate(MainActivity.this, fragments[position], args));
            fragmentTransaction.commit();

            // update selected item and title, then close the drawer
            mListView.setItemChecked(position, true);
            //setTitle(mFeatureTitles[position]);
            //mToolBarTitle = mFeatureTitles[position];
            //mTitle.setText(mToolBarTitle);
            mDrawerLayout.closeDrawer(mDrawerRelativeLayout);


        }else {
            mDrawerLayout.closeDrawer(mDrawerRelativeLayout);
        }*/

        if(position == 5){
            mDrawerLayout.closeDrawer(mDrawerRelativeLayout);
        }else {
            Bundle args = new Bundle();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, Fragment.instantiate(MainActivity.this, fragments[position], args));
            fragmentTransaction.commit();

            // update selected item and title, then close the drawer
            mListView.setItemChecked(position, true);
            //setTitle(mFeatureTitles[position]);
            //mToolBarTitle = mFeatureTitles[position];
            //mTitle.setText(mToolBarTitle);
            mDrawerLayout.closeDrawer(mDrawerRelativeLayout);
        }



    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        actionBarDrawerToggle.onConfigurationChanged(newConfig);

    }

    private void makeMeRequest() {
        Request request = Request.newMeRequest(ParseFacebookUtils.getSession(),
                new Request.GraphUserCallback() {
                    @Override
                    public void onCompleted(final GraphUser user, Response response) {
                        if (user != null) {


                            Picasso.with(MainActivity.this)
                                    .load("http://graph.facebook.com/" + user.getId() + "/picture?type=large")
                                    .placeholder(R.drawable.ic_profile_picture)
                                    .error(R.drawable.ic_profile_picture)
                                    .into(profilePicture);

                            /*AsyncTask<Void, Void, Bitmap> t = new AsyncTask<Void, Void, Bitmap>() {
                                protected Bitmap doInBackground(Void... p) {
                                    Bitmap bm = null;
                                    try {
                                        URL aURL = new URL("http://graph.facebook.com/" + user.getId() + "/picture?type=large");
                                        URLConnection conn = aURL.openConnection();
                                        conn.setUseCaches(true);
                                        conn.connect();
                                        InputStream is = conn.getInputStream();
                                        BufferedInputStream bis = new BufferedInputStream(is);
                                        bm = BitmapFactory.decodeStream(bis);
                                        bis.close();
                                        is.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    return bm;
                                }

                                protected void onPostExecute(Bitmap bm) {

                                    *//*Drawable drawable = new BitmapDrawable(getResources(), bm);

                                    photoImageView.setBackgroundDrawable(drawable);

                                    Picasso*//*

                                }
                            };
                            t.execute();*/


                        } else if (response.getError() != null) {
                            // handle error
                        }
                    }
                });
        request.executeAsync();
    }

}
