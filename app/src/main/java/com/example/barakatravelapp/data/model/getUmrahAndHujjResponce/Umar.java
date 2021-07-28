
package com.example.barakatravelapp.data.model.getUmrahAndHujjResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Umar implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("makkahDesciption")
    @Expose
    private String makkahDesciption;
    @SerializedName("madinaDesciption")
    @Expose
    private String madinaDesciption;
    @SerializedName("startDateInformat")
    @Expose
    private String startDateInformat;
    @SerializedName("endDateInformat")
    @Expose
    private String endDateInformat;
    @SerializedName("minPrice")
    @Expose
    private Integer minPrice;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("calRate")
    @Expose
    private Integer calRate;
    @SerializedName("packageType")
    @Expose
    private String packageType;
    @SerializedName("toCity")
    @Expose
    private String toCity;
    @SerializedName("fromCity")
    @Expose
    private String fromCity;
    @SerializedName("stopOverType")
    @Expose
    private String stopOverType;
    @SerializedName("desciption")
    @Expose
    private Object desciption;
    @SerializedName("is_offer")
    @Expose
    private Object isOffer;
    @SerializedName("flighting")
    @Expose
    private String flighting;
    @SerializedName("rituals")
    @Expose
    private String rituals;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMakkahDesciption() {
        return makkahDesciption;
    }

    public void setMakkahDesciption(String makkahDesciption) {
        this.makkahDesciption = makkahDesciption;
    }

    public String getMadinaDesciption() {
        return madinaDesciption;
    }

    public void setMadinaDesciption(String madinaDesciption) {
        this.madinaDesciption = madinaDesciption;
    }

    public String getStartDateInformat() {
        return startDateInformat;
    }

    public void setStartDateInformat(String startDateInformat) {
        this.startDateInformat = startDateInformat;
    }

    public String getEndDateInformat() {
        return endDateInformat;
    }

    public void setEndDateInformat(String endDateInformat) {
        this.endDateInformat = endDateInformat;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCalRate() {
        return calRate;
    }

    public void setCalRate(Integer calRate) {
        this.calRate = calRate;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getStopOverType() {
        return stopOverType;
    }

    public void setStopOverType(String stopOverType) {
        this.stopOverType = stopOverType;
    }

    public Object getDesciption() {
        return desciption;
    }

    public void setDesciption(Object desciption) {
        this.desciption = desciption;
    }

    public Object getIsOffer() {
        return isOffer;
    }

    public void setIsOffer(Object isOffer) {
        this.isOffer = isOffer;
    }

    public String getFlighting() {
        return flighting;
    }

    public void setFlighting(String flighting) {
        this.flighting = flighting;
    }

    public String getRituals() {
        return rituals;
    }

    public void setRituals(String rituals) {
        this.rituals = rituals;
    }

}
