package com.traato.nar.traato.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by nar on 2/27/2017.
 */

public class Utils {
    private static Gson gson;

    private Utils(){}

    public static Gson getGsonParser()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        return gson;
    }

}
