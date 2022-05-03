package com.EZALLC.securityapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface haveibeenpwndinterface {
    @Headers({"user-agent: RetrofitApp",
            "hibp-api-key: fc74388137994b6b9ca48ff3c7db31e0"})
    @GET("{account}")
    Call<List<Pwned>> getPwned(@Path(value = "account", encoded = true) String account, @Query("truncateResponse") Boolean fullness);
    }

