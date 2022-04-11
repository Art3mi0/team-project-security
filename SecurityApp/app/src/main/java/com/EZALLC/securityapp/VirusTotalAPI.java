package com.EZALLC.securityapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface VirusTotalAPI {

    @Headers({"Accept: application/json",
            "x-apikey: a3824b2a3fe8cf3511178d15fd648cc8fe86c01b14c7ab1cce6ff8578639a3c7"})
    @GET("ip_addresses/{ip}")
    Call<IpInfo> getIPInfo(@Path(value = "ip", encoded = true)String Ip);

    @Headers({"Accept: application/json",
            "x-apikey: a3824b2a3fe8cf3511178d15fd648cc8fe86c01b14c7ab1cce6ff8578639a3c7"})
    @GET("urls/{url}")
    Call<HashInfo> getHashInfo(@Path(value = "url", encoded = true)String url);
}

