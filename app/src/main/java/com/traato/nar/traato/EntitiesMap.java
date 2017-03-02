package com.traato.nar.traato;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.traato.nar.traato.api.EndPoints;
import com.traato.nar.traato.api.GsonRequest;
import com.traato.nar.traato.entities.CategoryEntity;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by nar on 2/28/2017.
 */

public class EntitiesMap {
    private static Map<Long, CategoryEntity> mIndexCategoryMap = new HashMap<>();

    public static CategoryEntity getCategoryGivenIndex(long id)
    {
     /*   if(mIndexCategoryMap.isEmpty() || mIndexCategoryMap.containsKey(id) == false)
        {
            getCategoryFromJsonFile(id);
        }
        else
        {
            CategoryEntity categoryEntity = mIndexCategoryMap.get(id);
            mIndexCategoryMap.put(id, categoryEntity);
        }*/

        if(mIndexCategoryMap.isEmpty() || mIndexCategoryMap.containsKey(id) == false)
            return null;

        return  mIndexCategoryMap.get(id);
    }

    /**
     * Should not be called directly
     * @param id
     * @return
     */
    private static void getCategoryFromJsonFile(long id)
    {
        final long tempId = id; //Added to stop compilation

        String url = String.format(EndPoints.CATEGORIES, id);
        GsonRequest<CategoryEntity> gsonRequest = new GsonRequest<>(Request.Method.GET, url, null, CategoryEntity.class, new Response.Listener<CategoryEntity>() {

            @Override
            public void onResponse(CategoryEntity categoryEnt) {
                Log.d("Response", categoryEnt.toString());

                mIndexCategoryMap.put(tempId, categoryEnt);
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

}
