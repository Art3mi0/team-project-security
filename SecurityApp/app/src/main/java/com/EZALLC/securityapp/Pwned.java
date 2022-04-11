package com.EZALLC.securityapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pwned {
    @SerializedName("Name")
    @Expose
    private String name;
    public String getName() {
        return name;
    }
}
