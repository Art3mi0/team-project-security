package com.EZALLC.securityapp;

import java.util.List;

public class Threat {

    private String type;
    private String id;
    private String regionalInternetRegistry;
    private String asOwner;
    private String continent;
    private String country;
    private int harmless;
    private int malicious;
    private int suspicious;
    private int undetected;
    private boolean isFavorite;
    private List<String> breaches;

    public Threat() {
    }

    public Threat(String type, String id, String regionalInternetRegistry, String asOwner, String continent, String country,
                  int harmless, int malicious, int suspicious, int undetected, boolean isFavorite, List<String> breaches) {
        this.type = type;
        this.id = id;
        this.regionalInternetRegistry = regionalInternetRegistry;
        this.asOwner = asOwner;
        this.continent = continent;
        this.country = country;
        this.harmless = harmless;
        this.malicious = malicious;
        this.suspicious = suspicious;
        this.undetected = undetected;
        this.isFavorite = isFavorite;
        this.breaches = breaches;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegionalInternetRegistry() {
        return regionalInternetRegistry;
    }

    public void setRegionalInternetRegistry(String regionalInternetRegistry) {
        this.regionalInternetRegistry = regionalInternetRegistry;
    }

    public String getAsOwner() {
        return asOwner;
    }

    public void setAsOwner(String asOwner) {
        this.asOwner = asOwner;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getHarmless() {
        return harmless;
    }

    public void setHarmless(int harmless) {
        this.harmless = harmless;
    }

    public int getMalicious() {
        return malicious;
    }

    public void setMalicious(int malicious) {
        this.malicious = malicious;
    }

    public int getSuspicious() {
        return suspicious;
    }

    public void setSuspicious(int suspicious) {
        this.suspicious = suspicious;
    }

    public int getUndetected() {
        return undetected;
    }

    public void setUndetected(int undetected) {
        this.undetected = undetected;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public List<String> getBreaches() {
        return breaches;
    }

    public void setBreaches(List<String> breaches) {
        this.breaches = breaches;
    }
}
