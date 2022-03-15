package com.EZALLC.securityapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Riskfactor {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("risk")
    @Expose
    private String risk;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}
