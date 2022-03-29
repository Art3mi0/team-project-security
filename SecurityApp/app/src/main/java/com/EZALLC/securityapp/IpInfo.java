package com.EZALLC.securityapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IpInfo {
    @SerializedName("data")
    @Expose
    private data1 data;

    public data1 getData() {
        return data;
    }

    public void setData(data1 data) {
        this.data = data;
    }
}
class data1 {
    @SerializedName("attributes")
    @Expose
    private attributes1 attributes;

    public attributes1 getAttributes() {
        return attributes;
    }

    public void setAttributes(attributes1 attributes) {
        this.attributes = attributes;
    }
}

class attributes1 {
    //    @SerializedName("regional_internet_registry")
//    @Expose
//    private String regionalInternetRegistry;
//    @SerializedName("network")
//    @Expose
//    private String network;
//    @SerializedName("tags")
//    @Expose
//    private List<Object> tags = null;
//    @SerializedName("country")
//    @Expose
//    private String country;
//    @SerializedName("as_owner")
//    @Expose
//    private String asOwner;
    @SerializedName("last_analysis_stats")
    @Expose
    private LastAnalysisStats lastAnalysisStats;
    @SerializedName("asn")
    @Expose
    private Integer asn;
//    @SerializedName("whois_date")
//    @Expose
//    private Integer whoisDate;
//    @SerializedName("last_analysis_results")
//    @Expose
//    private LastAnalysisResults lastAnalysisResults;

    //    public String getRegionalInternetRegistry() {
//        return regionalInternetRegistry;
//    }
//
//    public void setRegionalInternetRegistry(String regionalInternetRegistry) {
//        this.regionalInternetRegistry = regionalInternetRegistry;
//    }
//
//    public String getNetwork() {
//        return network;
//    }
//
//    public void setNetwork(String network) {
//        this.network = network;
//    }
//
//    public List<Object> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<Object> tags) {
//        this.tags = tags;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getAsOwner() {
//        return asOwner;
//    }
//
//    public void setAsOwner(String asOwner) {
//        this.asOwner = asOwner;
//    }
//
    public LastAnalysisStats getLastAnalysisStats() {
        return lastAnalysisStats;
    }

    public void setLastAnalysisStats(LastAnalysisStats lastAnalysisStats) {
        this.lastAnalysisStats = lastAnalysisStats;
    }

    public Integer getAsn() {
        return asn;
    }

    public void setAsn(Integer asn) {
        this.asn = asn;
    }

//    public Integer getWhoisDate() {
//        return whoisDate;
//    }
//
//    public void setWhoisDate(Integer whoisDate) {
//        this.whoisDate = whoisDate;
//    }
//
//    public LastAnalysisResults getLastAnalysisResults() {
//        return lastAnalysisResults;
//    }
//
//    public void setLastAnalysisResults(LastAnalysisResults lastAnalysisResults) {
//        this.lastAnalysisResults = lastAnalysisResults;
//    }
}


class LastAnalysisStats {
    @SerializedName("harmless")
    @Expose
    private Integer harmless;
    @SerializedName("malicious")
    @Expose
    private Integer malicious;
    @SerializedName("suspicious")
    @Expose
    private Integer suspicious;
    @SerializedName("undetected")
    @Expose
    private Integer undetected;
    @SerializedName("timeout")
    @Expose
    private Integer timeout;

    public Integer getHarmless() {
        return harmless;
    }

    public void setHarmless(Integer harmless) {
        this.harmless = harmless;
    }

    public Integer getMalicious() {
        return malicious;
    }

    public void setMalicious(Integer malicious) {
        this.malicious = malicious;
    }

    public Integer getSuspicious() {
        return suspicious;
    }

    public void setSuspicious(Integer suspicious) {
        this.suspicious = suspicious;
    }

    public Integer getUndetected() {
        return undetected;
    }

    public void setUndetected(Integer undetected) {
        this.undetected = undetected;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

}
