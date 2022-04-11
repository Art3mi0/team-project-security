package com.EZALLC.securityapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface haveibeenpwndinterface {
    @Headers({"user-agent: RetrofitApp",
            "hibp-api-key: fc31960407a9435a82fa5b1d9f212934"})
    @GET("{account}")
    Call<List<Pwned>> getPwned(@Path(value = "account", encoded = true) String account);
    }

