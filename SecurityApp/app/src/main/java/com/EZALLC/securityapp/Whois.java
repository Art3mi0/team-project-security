package com.EZALLC.securityapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Whois {

    @SerializedName("OrgAbuseEmail")
    @Expose
    private List<String> orgAbuseEmail = null;
    @SerializedName("OrgRoutingName")
    @Expose
    private String orgRoutingName;
    @SerializedName("OrgAbuseHandle")
    @Expose
    private List<String> orgAbuseHandle = null;
    @SerializedName("Ref")
    @Expose
    private List<String> ref = null;
    @SerializedName("OrgTechName")
    @Expose
    private List<String> orgTechName = null;
    @SerializedName("++registered")
    @Expose
    private String registered;
    @SerializedName("++updated")
    @Expose
    private String updated1;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("OrgRoutingPhone")
    @Expose
    private String orgRoutingPhone;
    @SerializedName("NetHandle")
    @Expose
    private String netHandle;
    @SerializedName("++registrant")
    @Expose
    private String registrant;
    @SerializedName("OrgName")
    @Expose
    private String orgName;
    @SerializedName("++phone")
    @Expose
    private String phone;
    @SerializedName("CIDR")
    @Expose
    private String cidr;
    @SerializedName("++email")
    @Expose
    private List<String> email = null;
    @SerializedName("Organization")
    @Expose
    private String organization;
    @SerializedName("OrgTechPhone")
    @Expose
    private String orgTechPhone;
    @SerializedName("PostalCode")
    @Expose
    private String postalCode;
    @SerializedName("StateProv")
    @Expose
    private String stateProv;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("OrgAbuseName")
    @Expose
    private List<String> orgAbuseName = null;
    @SerializedName("Updated")
    @Expose
    private List<String> updated = null;
    @SerializedName("++abuse")
    @Expose
    private List<String> abuse = null;
    @SerializedName("NetType")
    @Expose
    private String netType;
    @SerializedName("OrgTechHandle")
    @Expose
    private List<String> orgTechHandle = null;
    @SerializedName("OrgRoutingEmail")
    @Expose
    private String orgRoutingEmail;
    @SerializedName("OrgRoutingHandle")
    @Expose
    private String orgRoutingHandle;
    @SerializedName("OrgTechEmail")
    @Expose
    private List<String> orgTechEmail = null;
    @SerializedName("NetRange")
    @Expose
    private String netRange;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("NetName")
    @Expose
    private String netName;
    @SerializedName("Parent")
    @Expose
    private String parent;
    @SerializedName("OrgRoutingRef")
    @Expose
    private String orgRoutingRef;
    @SerializedName("OrgAbuseRef")
    @Expose
    private List<String> orgAbuseRef = null;
    @SerializedName("OrgTechRef")
    @Expose
    private List<String> orgTechRef = null;
    @SerializedName("RegDate")
    @Expose
    private List<String> regDate = null;
    @SerializedName("OrgId")
    @Expose
    private String orgId;
    @SerializedName("OrgAbusePhone")
    @Expose
    private String orgAbusePhone;

    public List<String> getOrgAbuseEmail() {
        return orgAbuseEmail;
    }

    public void setOrgAbuseEmail(List<String> orgAbuseEmail) {
        this.orgAbuseEmail = orgAbuseEmail;
    }

    public String getOrgRoutingName() {
        return orgRoutingName;
    }

    public void setOrgRoutingName(String orgRoutingName) {
        this.orgRoutingName = orgRoutingName;
    }

    public List<String> getOrgAbuseHandle() {
        return orgAbuseHandle;
    }

    public void setOrgAbuseHandle(List<String> orgAbuseHandle) {
        this.orgAbuseHandle = orgAbuseHandle;
    }

    public List<String> getRef() {
        return ref;
    }

    public void setRef(List<String> ref) {
        this.ref = ref;
    }

    public List<String> getOrgTechName() {
        return orgTechName;
    }

    public void setOrgTechName(List<String> orgTechName) {
        this.orgTechName = orgTechName;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getUpdated1() {
        return updated1;
    }

    public void setUpdated1(String updated1) {
        this.updated1 = updated1;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrgRoutingPhone() {
        return orgRoutingPhone;
    }

    public void setOrgRoutingPhone(String orgRoutingPhone) {
        this.orgRoutingPhone = orgRoutingPhone;
    }

    public String getNetHandle() {
        return netHandle;
    }

    public void setNetHandle(String netHandle) {
        this.netHandle = netHandle;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrgTechPhone() {
        return orgTechPhone;
    }

    public void setOrgTechPhone(String orgTechPhone) {
        this.orgTechPhone = orgTechPhone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStateProv() {
        return stateProv;
    }

    public void setStateProv(String stateProv) {
        this.stateProv = stateProv;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getOrgAbuseName() {
        return orgAbuseName;
    }

    public void setOrgAbuseName(List<String> orgAbuseName) {
        this.orgAbuseName = orgAbuseName;
    }

    public List<String> getUpdated() {
        return updated;
    }

    public void setUpdated(List<String> updated) {
        this.updated = updated;
    }

    public List<String> getAbuse() {
        return abuse;
    }

    public void setAbuse(List<String> abuse) {
        this.abuse = abuse;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public List<String> getOrgTechHandle() {
        return orgTechHandle;
    }

    public void setOrgTechHandle(List<String> orgTechHandle) {
        this.orgTechHandle = orgTechHandle;
    }

    public String getOrgRoutingEmail() {
        return orgRoutingEmail;
    }

    public void setOrgRoutingEmail(String orgRoutingEmail) {
        this.orgRoutingEmail = orgRoutingEmail;
    }

    public String getOrgRoutingHandle() {
        return orgRoutingHandle;
    }

    public void setOrgRoutingHandle(String orgRoutingHandle) {
        this.orgRoutingHandle = orgRoutingHandle;
    }

    public List<String> getOrgTechEmail() {
        return orgTechEmail;
    }

    public void setOrgTechEmail(List<String> orgTechEmail) {
        this.orgTechEmail = orgTechEmail;
    }

    public String getNetRange() {
        return netRange;
    }

    public void setNetRange(String netRange) {
        this.netRange = netRange;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getOrgRoutingRef() {
        return orgRoutingRef;
    }

    public void setOrgRoutingRef(String orgRoutingRef) {
        this.orgRoutingRef = orgRoutingRef;
    }

    public List<String> getOrgAbuseRef() {
        return orgAbuseRef;
    }

    public void setOrgAbuseRef(List<String> orgAbuseRef) {
        this.orgAbuseRef = orgAbuseRef;
    }

    public List<String> getOrgTechRef() {
        return orgTechRef;
    }

    public void setOrgTechRef(List<String> orgTechRef) {
        this.orgTechRef = orgTechRef;
    }

    public List<String> getRegDate() {
        return regDate;
    }

    public void setRegDate(List<String> regDate) {
        this.regDate = regDate;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgAbusePhone() {
        return orgAbusePhone;
    }

    public void setOrgAbusePhone(String orgAbusePhone) {
        this.orgAbusePhone = orgAbusePhone;
    }

}
