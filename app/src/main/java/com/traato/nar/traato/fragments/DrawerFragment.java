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
import com.traato.nar.traato.MyApplication;
import com.traato.nar.traato.R;
import com.traato.nar.traato.api.GsonRequest;
import com.traato.nar.traato.entities.DrawerItemCategory;

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
        String url = "http://truebaniya.com/traato/traato/api/categories/149?output_format=JSON";
        GsonRequest<DrawerItemCategory> gsonRequest = new GsonRequest<>(Request.Method.GET, url, null, DrawerItemCategory.class, new Response.Listener<DrawerItemCategory>() {

            @Override
            public void onResponse(DrawerItemCategory response) {
                Log.d("Response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());
            }
        }, null, "VJW7YLU22B82GAV2S62NTY4G8AX7QA5C");

        MyApplication instance = MyApplication.getInstance();
        instance.addToRequestQueue(gsonRequest, "DrawerItems");


    }
}




