package com.raider.addressthroughpincode.com.raider.addressthroughpincode.dto;

public class PostOffices {

    private String name;
    private String description;
    private String branchType;
    private String district;
    private String divison;
    private String region;
    private String state;
    private String country;

    public PostOffices(String name, String description, String branchType, String district, String divison, String region, String state, String country) {
        this.name = name;
        this.description = description;
        this.branchType = branchType;
        this.district = district;
        this.divison = divison;
        this.region = region;
        this.state = state;
        this.country = country;
    }

    public PostOffices() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDivison() {
        return divison;
    }

    public void setDivison(String divison) {
        this.divison = divison;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "PostOffices{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", branchType='" + branchType + '\'' +
                ", district='" + district + '\'' +
                ", divison='" + divison + '\'' +
                ", region='" + region + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
