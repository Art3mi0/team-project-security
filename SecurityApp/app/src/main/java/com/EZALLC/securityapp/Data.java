package com.EZALLC.securityapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("properties")
    @Expose
    private Properties properties;
    //    @SerializedName("risk_recommended")
//    @Expose
//    private String riskRecommended;
    @SerializedName("risk")
    @Expose
    private String risk;
//    @SerializedName("riskfactors")
//    @Expose
//    private List<Riskfactor> riskfactors = null;
//    @SerializedName("umbrella_domain")
//    @Expose
//    private Object umbrellaDomain;
//    @SerializedName("base64")
//    @Expose
//    private String base64;
//    @SerializedName("ioc_base64")
//    @Expose
//    private String iocBase64;
//    @SerializedName("type")
//    @Expose
//    private String type;
//    @SerializedName("attributes")
//    @Expose
//    private List<Object> attributes = null;
//    @SerializedName("links")
//    @Expose
//    private List<Object> links = null;
//    @SerializedName("umbrella_rank")
//    @Expose
//    private Object umbrellaRank;
//    @SerializedName("indicator")
//    @Expose
//    private String indicator;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    //    public String getRiskRecommended() {
//        return riskRecommended;
//    }
//
//    public void setRiskRecommended(String riskRecommended) {
//        this.riskRecommended = riskRecommended;
//    }
//
    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
//
//    public List<Riskfactor> getRiskfactors() {
//        return riskfactors;
//    }
//
//    public void setRiskfactors(List<Riskfactor> riskfactors) {
//        this.riskfactors = riskfactors;
//    }
//
//    public Object getUmbrellaDomain() {
//        return umbrellaDomain;
//    }
//
//    public void setUmbrellaDomain(Object umbrellaDomain) {
//        this.umbrellaDomain = umbrellaDomain;
//    }
//
//    public String getBase64() {
//        return base64;
//    }
//
//    public void setBase64(String base64) {
//        this.base64 = base64;
//    }
//
//    public String getIocBase64() {
//        return iocBase64;
//    }
//
//    public void setIocBase64(String iocBase64) {
//        this.iocBase64 = iocBase64;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public List<Object> getAttributes() {
//        return attributes;
//    }
//
//    public void setAttributes(List<Object> attributes) {
//        this.attributes = attributes;
//    }
//
//    public List<Object> getLinks() {
//        return links;
//    }
//
//    public void setLinks(List<Object> links) {
//        this.links = links;
//    }
//
//    public Object getUmbrellaRank() {
//        return umbrellaRank;
//    }
//
//    public void setUmbrellaRank(Object umbrellaRank) {
//        this.umbrellaRank = umbrellaRank;
//    }
//
//    public String getIndicator() {
//        return indicator;
//    }
//
//    public void setIndicator(String indicator) {
//        this.indicator = indicator;
//    }
//

}
