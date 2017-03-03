package com.traato.nar.traato.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.traato.nar.traato.adapters.DrawerRecyclerAdapter;
import com.traato.nar.traato.api.EndPoints;
import com.traato.nar.traato.api.GsonRequest;
import com.traato.nar.traato.entities.AssociationItem;
import com.traato.nar.traato.entities.AssociationItemList;
import com.traato.nar.traato.entities.CategoryEntity;
import com.traato.nar.traato.entities.GsonCategory;

import java.util.List;

/**
 * Created by nar on 2/24/2017.
 */

public class DrawerFragment extends Fragment {

    private RecyclerView mRecyclerview;
    private DrawerRecyclerAdapter mRecyclerAdapter = null;
    private View mLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = inflater.inflate(R.layout.fragment_drawer,  container, false);

        getDrawerItems();

        //prepareDrawerRecyclerView(mLayout);

        return mLayout;
    }

    public void getDrawerItems()
    {
        String url = String.format(EndPoints.CATEGORIES, CONST.HOME_CATEGORY);
        GsonRequest<CategoryEntity> gsonRequest = new GsonRequest<>(Request.Method.GET, url, null, CategoryEntity.class, new Response.Listener<CategoryEntity>() {

            @Override
            public void onResponse(CategoryEntity categoryEnt) {
                Log.d("Response", categoryEnt.toString());
                prepareDrawerRecyclerView(mLayout);
                mRecyclerAdapter.addCategoryItem(categoryEnt);
               // mRecyclerAdapter.notifyDataSetChanged();

                GsonCategory gsonCategory = categoryEnt.getGsonCategory();

                AssociationItemList assocItemList = gsonCategory.getAssociations();
                final List<AssociationItem> subGsonCategories = assocItemList.getCategories();
                if(subGsonCategories.isEmpty())
                    return;

                int count = subGsonCategories.size();
                final long lastId = subGsonCategories.get(count-1).getid();
                for(int ii = 0; ii < count; ++ii)
                {
                    long id = subGsonCategories.get(ii).getid();

                    CategoryEntity catEnt = EntitiesMap.getCategoryGivenIndex(id);

                    if(catEnt == null)
                    {
                        String url = String.format(EndPoints.CATEGORIES, id);
                        GsonRequest<CategoryEntity> gsonRequest = new GsonRequest<>(Request.Method.GET, url, null, CategoryEntity.class, new Response.Listener<CategoryEntity>() {

                            @Override
                            public void onResponse(CategoryEntity categoryEnt) {

                                mRecyclerAdapter.addCategoryItem(categoryEnt);
                                //GsonCategory gsonCategory = categoryEnt.getGsonCategory();
                                //if(lastId == gsonCategory.getGsonCategoryId()) {
                                    mRecyclerAdapter.notifyDataSetChanged();
                                //}

                            if (mRecyclerview != null) mRecyclerview.setVisibility(View.VISIBLE);
                            //if (mRecyclerview != null) mRecyclerview.setVisibility(View.GONE);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Error", error.toString());
                            }

                        }, null, CONST.ACCESS_TOKEN);

                        MyApplication instance = MyApplication.getInstance();
                        instance.addToRequestQueue(gsonRequest, "SubDrawerItems");
                    }
                    else
                    {
                        mRecyclerAdapter.addCategoryItem(catEnt);
                        mRecyclerAdapter.notifyDataSetChanged();

                        if (mRecyclerview != null) mRecyclerview.setVisibility(View.VISIBLE);
                        //if (mRecyclerview != null) mRecyclerview.setVisibility(View.GONE);
                    }


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());
            }
        }, null, CONST.ACCESS_TOKEN);

        MyApplication instance = MyApplication.getInstance();
        instance.addToRequestQueue(gsonRequest, "DrawerItems");
    }

    public void prepareDrawerRecyclerView(View view)
    {
        mRecyclerview = (RecyclerView) view.findViewById(R.id.drawer_recycler);
        mRecyclerAdapter = new DrawerRecyclerAdapter(getContext());
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setAdapter(mRecyclerAdapter);

    }
}




