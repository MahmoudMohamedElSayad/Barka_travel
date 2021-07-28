
package com.example.barakatravelapp.data.model.getHotelsResponce;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetHotelsResponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("hotels")
    @Expose
    private List<HotelData> hotels = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public List<HotelData> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelData> hotels) {
        this.hotels = hotels;
    }

}
