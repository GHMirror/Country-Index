package com.zybooks.countryindex;

import java.io.Serializable;

public class Countries implements Serializable {
    private String name;
    private String flagKey;
    private String capital;
    private String continent;
    private String currency;
    private String language;
    private String foods;
    private String religion;
    private String region;
    private String population;
    private String government;
    private String latitude;
    private String longitude;

    public Countries(String name,
                     String flagKey,
                     String capital,
                     String continent,
                     String currency,
                     String language,
                     String foods,
                     String religion,
                     String region,
                     String population,
                     String government,
                     String latitude,
                     String longitude) {
        this.name = name;
        this.flagKey = flagKey;
        this.capital = capital;
        this.continent = continent;
        this.currency = currency;
        this.language = language;
        this.foods = foods;
        this.religion = religion;
        this.region = region;
        this.population = population;
        this.government = government;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCountryName()
    {
        return name;
    }

    public String getFlagKey() {
        return flagKey;
    }

    public String getCapital() { return capital; }

    public String getContinent() {
        return continent;
    }

    public String getCurrency() { return currency; }

    public String getLanguage() {
        return language;
    }

    public String getFoods() {
        return foods;
    }

    public String getReligion() {
        return religion;
    }

    public String getRegion() {
        return region;
    }

    public String getPopulation() { return population; }

    public String getGovernment() { return government; }

    public String getLatitude() { return latitude; }

    public String getLongitude() { return longitude; }
}
