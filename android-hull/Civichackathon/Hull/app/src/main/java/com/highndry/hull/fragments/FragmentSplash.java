package com.highndry.hull.fragments;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AppEventsLogger;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.highndry.hull.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by ahmadalinasir on 2/21/15.
 *
 */
public class FragmentSplash extends Fragment {


    private static final String TAG = "HULL";
    private UiLifecycleHelper uiHelper;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
        authButton.setFragment(this);
        authButton.setReadPermissions(Arrays.asList("user_likes", "user_status"));

        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "com.highndry.hull",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i(TAG, "Logged in...");

           /* ParseUser user = new ParseUser();
            user.setUsername(session.);
            user.setPassword("noodlepoodle");
            user.setEmail("noodle@hullchalao.com");

            // other fields can be set just like with ParseObject
            user.put("phone", "650-555-0000");

            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        // Hooray! Let them use the app now.
                    } else {
                        // Sign up didn't succeed. Look at the ParseException
                        // to figure out what went wrong
                    }
                }
            });*/

        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
        }
    }

    private Session.StatusCallback callback = new Session.StatusCallback() {
        private String fbAccessToken;

        @Override
        public void call(Session session, SessionState state, Exception exception) {
            //updateView();
            onSessionStateChange(session, state, exception);
            if (session.isOpened()) {
                fbAccessToken = session.getAccessToken();
                // make request to get facebook user info
                Request.newMeRequest(session, new Request.GraphUserCallback() {
                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                        Log.i("fb", "fb user: "+ user.toString());

                        String fbId = user.getId();
                        String fbAccessToken_ = fbAccessToken;
                        String fbName = user.getName();
                        String gender = user.asMap().get("gender").toString();
                        String email = user.asMap().get("email").toString();



                        //Log.i("fb", userProfile.getEmail());
                    }
                });
            }
        }
    };

/*
    private class SessionStatusCallback implements Session.StatusCallback {
        private String fbAccessToken;

        @Override
        public void call(Session session, SessionState state, Exception exception) {
            //updateView();
            onSessionStateChange(session, state, exception);
            if (session.isOpened()) {
                fbAccessToken = session.getAccessToken();
                // make request to get facebook user info
                Request.newMeRequest(session, new Request.GraphUserCallback() {
                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                        Log.i("fb", "fb user: "+ user.toString());

                        String fbId = user.getId();
                        String fbAccessToken_ = fbAccessToken;
                        String fbName = user.getName();
                        String gender = user.asMap().get("gender").toString();
                        String email = user.asMap().get("email").toString();

                        //Log.i("fb", userProfile.getEmail());
                    }
                });
            }
        }
    }
*/


}
