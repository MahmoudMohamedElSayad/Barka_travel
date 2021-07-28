
package com.example.barakatravelapp.data.model.getDiscoverHomeResponce;

import java.util.List;

import com.example.barakatravelapp.data.model.getHotelsResponce.HotelData;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetTopUmarAndTophajjPackage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDiscoverHomeResponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("getTopUmar")
    @Expose
    private List<GetTopUmarAndTophajjPackage> getTopUmar = null;
    @SerializedName("getTophajj")
    @Expose
    private List<GetTopUmarAndTophajjPackage> getTophajj = null;
    @SerializedName("getTopRate")
    @Expose
    private List<Object> getTopRate = null;
    @SerializedName("getTopHotels")
    @Expose
    private List<HotelData> getTopHotels = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public List<GetTopUmarAndTophajjPackage> getGetTopUmar() {
        return getTopUmar;
    }

    public void setGetTopUmar(List<GetTopUmarAndTophajjPackage> getTopUmar) {
        this.getTopUmar = getTopUmar;
    }

    public List<GetTopUmarAndTophajjPackage> getGetTophajj() {
        return getTophajj;
    }

    public void setGetTophajj(List<GetTopUmarAndTophajjPackage> getTophajj) {
        this.getTophajj = getTophajj;
    }

    public List<Object> getGetTopRate() {
        return getTopRate;
    }

    public void setGetTopRate(List<Object> getTopRate) {
        this.getTopRate = getTopRate;
    }

    public List<HotelData> getGetTopHotels() {
        return getTopHotels;
    }

    public void setGetTopHotels(List<HotelData> getTopHotels) {
        this.getTopHotels = getTopHotels;
    }

}
