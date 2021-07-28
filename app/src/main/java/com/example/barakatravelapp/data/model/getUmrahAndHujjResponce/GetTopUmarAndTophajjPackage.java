
package com.example.barakatravelapp.data.model.getUmrahAndHujjResponce;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetTopUmarAndTophajjPackage implements Serializable {

    @SerializedName("umar")
    @Expose
    private Umar umar;
    @SerializedName("umarImages")
    @Expose
    private List<String> umarImages = null;
    @SerializedName("packagesInclude")
    @Expose
    private List<PackagesInclude> packagesInclude = null;
    @SerializedName("pricing")
    @Expose
    private List<Pricing> pricing = null;
    @SerializedName("rates")
    @Expose
    private Rates rates;
    @SerializedName("umarhDays")
    @Expose
    private List<UmarhDay> umarhDays = null;
    @SerializedName("umarhDetails")
    @Expose
    private UmarhDetails umarhDetails;
    @SerializedName("stopOver")
    @Expose
    private List<Object> stopOver = null;

    public Umar getUmar() {
        return umar;
    }

    public void setUmar(Umar umar) {
        this.umar = umar;
    }

    public List<String> getUmarImages() {
        return umarImages;
    }

    public void setUmarImages(List<String> umarImages) {
        this.umarImages = umarImages;
    }

    public List<PackagesInclude> getPackagesInclude() {
        return packagesInclude;
    }

    public void setPackagesInclude(List<PackagesInclude> packagesInclude) {
        this.packagesInclude = packagesInclude;
    }

    public List<Pricing> getPricing() {
        return pricing;
    }

    public void setPricing(List<Pricing> pricing) {
        this.pricing = pricing;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public List<UmarhDay> getUmarhDays() {
        return umarhDays;
    }

    public void setUmarhDays(List<UmarhDay> umarhDays) {
        this.umarhDays = umarhDays;
    }

    public UmarhDetails getUmarhDetails() {
        return umarhDetails;
    }

    public void setUmarhDetails(UmarhDetails umarhDetails) {
        this.umarhDetails = umarhDetails;
    }

    public List<Object> getStopOver() {
        return stopOver;
    }

    public void setStopOver(List<Object> stopOver) {
        this.stopOver = stopOver;
    }

}
