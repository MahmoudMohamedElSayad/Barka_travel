
package com.example.barakatravelapp.data.model.getBookingHotelsResponce;

import com.example.barakatravelapp.data.model.getHotelsResponce.HotelData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingsHotel {

    @SerializedName("orderHotelId")
    @Expose
    private Integer orderHotelId;
    @SerializedName("reservefrom")
    @Expose
    private String reservefrom;
    @SerializedName("reserveto")
    @Expose
    private String reserveto;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("hotel")
    @Expose
    private HotelData hotel;
    @SerializedName("roomPrice")
    @Expose
    private String roomPrice;
    @SerializedName("totalPrice")
    @Expose
    private String totalPrice;

    public Integer getOrderHotelId() {
        return orderHotelId;
    }

    public void setOrderHotelId(Integer orderHotelId) {
        this.orderHotelId = orderHotelId;
    }

    public String getReservefrom() {
        return reservefrom;
    }

    public void setReservefrom(String reservefrom) {
        this.reservefrom = reservefrom;
    }

    public String getReserveto() {
        return reserveto;
    }

    public void setReserveto(String reserveto) {
        this.reserveto = reserveto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HotelData getHotel() {
        return hotel;
    }

    public void setHotel(HotelData hotel) {
        this.hotel = hotel;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

}
