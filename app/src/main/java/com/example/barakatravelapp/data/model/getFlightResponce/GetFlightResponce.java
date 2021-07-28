
package com.example.barakatravelapp.data.model.getFlightResponce;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetFlightResponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("flights")
    @Expose
    private List<FlightData> flights = null;
//    @SerializedName("flights")
//    @Expose
//    private GetFlightPagination data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public List<FlightData> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightData> flights) {
        this.flights = flights;
    }


//    public GetFlightPagination getData() {
//        return data;
//    }
//
//    public void setData(GetFlightPagination data) {
//        this.data = data;
//    }



}
