
package com.example.barakatravelapp.data.model.getBookingFlightsResponce;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBookingFlightsResponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("flights")
    @Expose
    private List<BookingFlight> flights = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public List<BookingFlight> getFlights() {
        return flights;
    }

    public void setFlights(List<BookingFlight> flights) {
        this.flights = flights;
    }

}
