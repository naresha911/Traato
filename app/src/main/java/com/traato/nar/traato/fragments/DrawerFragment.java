package com.traato.nar.traato.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.traato.nar.traato.MyApplication;
import com.traato.nar.traato.R;

/**
 * Created by nar on 2/24/2017.
 */

public class DrawerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_drawer,  container, false);
        getDrawerItems();
        return layout;
    }

    public void getDrawerItems()
    {
        String url = "http://truebaniya.com/traato/traato/api/customers";
        //String url = "http://www.pcworld.com/index.rss";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());
            }
        });

        MyApplication instance = MyApplication.getInstance();
        instance.addToRequestQueue(stringRequest, "DrawerItems");




    }
}
