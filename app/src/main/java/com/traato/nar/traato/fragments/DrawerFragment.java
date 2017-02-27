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
import com.traato.nar.traato.api.GsonRequest;

import java.util.ArrayList;
import java.util.List;

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
        GsonRequest<> stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
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

/*
class Root
{
    private List<Category> categories;
    public void setCategories(List<Category> categories)
    {
        this.categories = categories;
    }

    public String toString()
    {
        String str = "";
        for(int ii = 0; ii < categories.size(); ++ii)
        {
            String str2 = String.format("CatId : %d\n", categories.get(ii).getid());
            str += str2;
        }
        return str;
    }
}

class Category
{
    private long id;
    public Category() {}
    public void setid(long id)
    {
        this.id = id;
    }
    public long getid()
    {
        return id;
    }
}
 */
