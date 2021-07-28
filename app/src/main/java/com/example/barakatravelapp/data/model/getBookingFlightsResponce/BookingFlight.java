
package com.example.barakatravelapp.data.model.getBookingFlightsResponce;

import com.example.barakatravelapp.data.model.getFlightResponce.FlightData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingFlight {

    @SerializedName("flight")
    @Expose
    private FlightData flight;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("userflightId")
    @Expose
    private Integer userflightId;
    @SerializedName("numOfChild")
    @Expose
    private Integer numOfChild;
    @SerializedName("numOfAdults")
    @Expose
    private Integer numOfAdults;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;

    public FlightData getFlight() {
        return flight;
    }

    public void setFlight(FlightData flight) {
        this.flight = flight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserflightId() {
        return userflightId;
    }

    public void setUserflightId(Integer userflightId) {
        this.userflightId = userflightId;
    }

    public Integer getNumOfChild() {
        return numOfChild;
    }

    public void setNumOfChild(Integer numOfChild) {
        this.numOfChild = numOfChild;
    }

    public Integer getNumOfAdults() {
        return numOfAdults;
    }

    public void setNumOfAdults(Integer numOfAdults) {
        this.numOfAdults = numOfAdults;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

}
