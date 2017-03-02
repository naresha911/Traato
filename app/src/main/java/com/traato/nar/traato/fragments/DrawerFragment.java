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
import com.traato.nar.traato.CONST;
import com.traato.nar.traato.EntitiesMap;
import com.traato.nar.traato.MyApplication;
import com.traato.nar.traato.R;
import com.traato.nar.traato.api.GsonRequest;
import com.traato.nar.traato.entities.CategoryEntity;

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
        EntitiesMap.getCategoryGivenIndex(CONST.HOME_CATEGORY);
    }
}




