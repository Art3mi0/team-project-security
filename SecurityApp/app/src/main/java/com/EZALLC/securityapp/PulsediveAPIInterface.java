package com.EZALLC.securityapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PulsediveAPIInterface {

    @GET("analyze.php")
    Call<Todo> getTodo(
            @Query("indicator") String indicator,
            @Query("pretty") String pretty
    );

    @GET("analyze.php")
    Call<Post> getPost(
            @Query("qid") String qid,
            @Query("pretty") String pretty
    );
}
