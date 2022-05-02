package com.EZALLC.securityapp;

import java.util.Comparator;

public class Recent {

    private String type;
    private String search;
    private int number;

    public Recent(){}

    public Recent(String type, String search, int number) {
        this.type = type;
        this.search = search;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

class Sortbynumber implements Comparator<Recent> {
    public int compare(Recent a, Recent b) {
        return b.getNumber() - a.getNumber();
    }
}
