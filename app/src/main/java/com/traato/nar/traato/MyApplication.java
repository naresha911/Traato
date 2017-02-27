package com.traato.nar.traato;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;
import com.traato.nar.traato.api.OkHttpStack;

import java.io.IOException;
import java.util.Map;

/**
 * Created by nar on 2/24/2017.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;
    private RequestQueue mRequestQueue;

    public static String APP_VERSION = "0.0.0";
    public static String ANDROID_ID = "0000000000000000";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        try {
            ANDROID_ID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            if (ANDROID_ID == null || ANDROID_ID.isEmpty()) {
                ANDROID_ID = "0000000000000000";
            }
        } catch (Exception e) {
            ANDROID_ID = "0000000000000000";
        }
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            APP_VERSION = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            //Timber.e(e, "App versionName not found. WTF?. This should never happen.");
        }


    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////// Volley request ///////////////////////////////////////////////////////////////////////////////////////
    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack());
        }

        return mRequestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        mRequestQueue = requestQueue;
    }

   public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        //req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
    //////////////////////// end of Volley request. ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
