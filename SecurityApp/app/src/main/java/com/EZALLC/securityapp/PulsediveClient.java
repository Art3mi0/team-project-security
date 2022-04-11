package com.EZALLC.securityapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PulsediveClient {
    private static final String BASE_URL = "https://haveibeenpwned.com/api/v3/breachedaccount/";

    private static Retrofit retrofit = null;

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
