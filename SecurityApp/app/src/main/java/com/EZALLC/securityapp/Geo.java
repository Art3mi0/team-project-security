package com.EZALLC.securityapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Geo {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private List<String> city = null;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("isp")
    @Expose
    private String isp;
    @SerializedName("asn")
    @Expose
    private String asn;
    @SerializedName("zip")
    @Expose
    private List<String> zip = null;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("long")
    @Expose
    private Double _long;
    @SerializedName("region")
    @Expose
    private List<String> region = null;
    @SerializedName("org")
    @Expose
    private List<String> org = null;
    @SerializedName("countrycode")
    @Expose
    private String countrycode;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getCity() {
        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getAsn() {
        return asn;
    }

    public void setAsn(String asn) {
        this.asn = asn;
    }

    public List<String> getZip() {
        return zip;
    }

    public void setZip(List<String> zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLong() {
        return _long;
    }

    public void setLong(Double _long) {
        this._long = _long;
    }

    public List<String> getRegion() {
        return region;
    }

    public void setRegion(List<String> region) {
        this.region = region;
    }

    public List<String> getOrg() {
        return org;
    }

    public void setOrg(List<String> org) {
        this.org = org;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

}
