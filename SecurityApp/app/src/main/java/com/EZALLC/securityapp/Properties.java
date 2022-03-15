package com.EZALLC.securityapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties {

    @SerializedName("geo")
    @Expose
    private Geo geo;
    @SerializedName("whois")
    @Expose
    private Whois whois;

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public Whois getWhois() {
        return whois;
    }

    public void setWhois(Whois whois) {
        this.whois = whois;
    }
}
