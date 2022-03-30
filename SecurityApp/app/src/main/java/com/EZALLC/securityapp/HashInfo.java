package com.EZALLC.securityapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HashInfo {

    @SerializedName("data")
    @Expose
    private Data69 data;

    public Data69 getData() {
        return data;
    }

    public void setData(Data69 data) {
        this.data = data;
    }

}

class Data69{
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}

class Attributes{
    @SerializedName("last_modification_date")
    @Expose
    private Integer lastModificationDate;
//    @SerializedName("last_http_response_cookies")
//    @Expose
//    private LastHttpResponseCookies lastHttpResponseCookies;
    @SerializedName("times_submitted")
    @Expose
    private Integer timesSubmitted;
    @SerializedName("total_votes")
    @Expose
    private TotalVotes totalVotes;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("last_submission_date")
    @Expose
    private Integer lastSubmissionDate;
    @SerializedName("last_http_response_content_length")
    @Expose
    private Integer lastHttpResponseContentLength;
//    @SerializedName("last_http_response_headers")
//    @Expose
//    private LastHttpResponseHeaders lastHttpResponseHeaders;
    @SerializedName("reputation")
    @Expose
    private Integer reputation;
    @SerializedName("threat_names")
    @Expose
    private List<Object> threatNames = null;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("last_analysis_date")
    @Expose
    private Integer lastAnalysisDate;
    @SerializedName("first_submission_date")
    @Expose
    private Integer firstSubmissionDate;
//    @SerializedName("categories")
//    @Expose
//    private Categories categories;
    @SerializedName("last_http_response_content_sha256")
    @Expose
    private String lastHttpResponseContentSha256;
    @SerializedName("last_http_response_code")
    @Expose
    private Integer lastHttpResponseCode;
    @SerializedName("last_final_url")
    @Expose
    private String lastFinalUrl;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("last_analysis_stats")
    @Expose
    private LastAnalysisStats lastAnalysisStats;

    public Integer getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Integer lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

//    public LastHttpResponseCookies getLastHttpResponseCookies() {
//        return lastHttpResponseCookies;
//    }
//
//    public void setLastHttpResponseCookies(LastHttpResponseCookies lastHttpResponseCookies) {
//        this.lastHttpResponseCookies = lastHttpResponseCookies;
//    }

    public Integer getTimesSubmitted() {
        return timesSubmitted;
    }

    public void setTimesSubmitted(Integer timesSubmitted) {
        this.timesSubmitted = timesSubmitted;
    }

    public TotalVotes getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(TotalVotes totalVotes) {
        this.totalVotes = totalVotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLastSubmissionDate() {
        return lastSubmissionDate;
    }

    public void setLastSubmissionDate(Integer lastSubmissionDate) {
        this.lastSubmissionDate = lastSubmissionDate;
    }

    public Integer getLastHttpResponseContentLength() {
        return lastHttpResponseContentLength;
    }

    public void setLastHttpResponseContentLength(Integer lastHttpResponseContentLength) {
        this.lastHttpResponseContentLength = lastHttpResponseContentLength;
    }

//    public LastHttpResponseHeaders getLastHttpResponseHeaders() {
//        return lastHttpResponseHeaders;
//    }
//
//    public void setLastHttpResponseHeaders(LastHttpResponseHeaders lastHttpResponseHeaders) {
//        this.lastHttpResponseHeaders = lastHttpResponseHeaders;
//    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public List<Object> getThreatNames() {
        return threatNames;
    }

    public void setThreatNames(List<Object> threatNames) {
        this.threatNames = threatNames;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public Integer getLastAnalysisDate() {
        return lastAnalysisDate;
    }

    public void setLastAnalysisDate(Integer lastAnalysisDate) {
        this.lastAnalysisDate = lastAnalysisDate;
    }

    public Integer getFirstSubmissionDate() {
        return firstSubmissionDate;
    }

    public void setFirstSubmissionDate(Integer firstSubmissionDate) {
        this.firstSubmissionDate = firstSubmissionDate;
    }

//    public Categories getCategories() {
//        return categories;
//    }
//
//    public void setCategories(Categories categories) {
//        this.categories = categories;
//    }

    public String getLastHttpResponseContentSha256() {
        return lastHttpResponseContentSha256;
    }

    public void setLastHttpResponseContentSha256(String lastHttpResponseContentSha256) {
        this.lastHttpResponseContentSha256 = lastHttpResponseContentSha256;
    }

    public Integer getLastHttpResponseCode() {
        return lastHttpResponseCode;
    }

    public void setLastHttpResponseCode(Integer lastHttpResponseCode) {
        this.lastHttpResponseCode = lastHttpResponseCode;
    }

    public String getLastFinalUrl() {
        return lastFinalUrl;
    }

    public void setLastFinalUrl(String lastFinalUrl) {
        this.lastFinalUrl = lastFinalUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LastAnalysisStats getLastAnalysisStats() {
        return lastAnalysisStats;
    }

    public void setLastAnalysisStats(LastAnalysisStats lastAnalysisStats) {
        this.lastAnalysisStats = lastAnalysisStats;
    }

}

class TotalVotes{
    @SerializedName("harmless")
    @Expose
    private Integer harmless;
    @SerializedName("malicious")
    @Expose
    private Integer malicious;

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

}