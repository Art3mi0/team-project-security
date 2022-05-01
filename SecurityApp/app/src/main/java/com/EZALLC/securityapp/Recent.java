package com.EZALLC.securityapp;

public class Recent {

    private String type;
    private String search;

    public Recent(){}

    public Recent(String type, String search) {
        this.type = type;
        this.search = search;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
