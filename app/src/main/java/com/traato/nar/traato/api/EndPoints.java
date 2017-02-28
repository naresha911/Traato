package com.traato.nar.traato.api;

/**
 * Created by nar on 2/28/2017.
 */

public class EndPoints {
    /**
     * Base server url
     */

    public static final String API_URL = "http://truebaniya.com/traato/traato/api/";

    public static final String OUTPUT_FORMAT = "output_format=JSON";
    public static final String CATEGORIES = API_URL.concat("categories/%d").concat(OUTPUT_FORMAT);
}
