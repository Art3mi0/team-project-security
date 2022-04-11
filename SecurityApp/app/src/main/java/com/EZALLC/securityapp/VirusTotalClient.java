package com.EZALLC.securityapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VirusTotalClient {
    private static final String BASE_URL = "https://www.virustotal.com/api/v3/";

    private static Retrofit retrofit = null;
    //.addConverterFactory(GsonConverterFactory.create())
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

