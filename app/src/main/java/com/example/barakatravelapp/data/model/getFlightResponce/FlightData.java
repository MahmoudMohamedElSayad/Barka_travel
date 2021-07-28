
package com.example.barakatravelapp.data.model.getFlightResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FlightData implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("flightName")
    @Expose
    private String flightName;
    @SerializedName("flightCompany")
    @Expose
    private String flightCompany;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("reservationFrom")
    @Expose
    private String reservationFrom;
    @SerializedName("reservationTo")
    @Expose
    private String reservationTo;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("priceAdult")
    @Expose
    private Double priceAdult;
    @SerializedName("priceChild")
    @Expose
    private Double priceChild;
    @SerializedName("isOffer")
    @Expose
    private Boolean isOffer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFlightCompany() {
        return flightCompany;
    }

    public void setFlightCompany(String flightCompany) {
        this.flightCompany = flightCompany;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getReservationFrom() {
        return reservationFrom;
    }

    public void setReservationFrom(String reservationFrom) {
        this.reservationFrom = reservationFrom;
    }

    public String getReservationTo() {
        return reservationTo;
    }

    public void setReservationTo(String reservationTo) {
        this.reservationTo = reservationTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPriceAdult() {
        return priceAdult;
    }

    public void setPriceAdult(Double priceAdult) {
        this.priceAdult = priceAdult;
    }

    public Double getPriceChild() {
        return priceChild;
    }

    public void setPriceChild(Double priceChild) {
        this.priceChild = priceChild;
    }

    public Boolean getIsOffer() {
        return isOffer;
    }

    public void setIsOffer(Boolean isOffer) {
        this.isOffer = isOffer;
    }

}
