
package com.example.barakatravelapp.data.model.getHotelsResponce;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelData  implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("availbleTickets")
    @Expose
    private Integer availbleTickets;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("hotelImages")
    @Expose
    private List<String> hotelImages = null;
    @SerializedName("getAmenities")
    @Expose
    private List<GetAmenity> getAmenities = null;
    @SerializedName("getRooms")
    @Expose
    private List<GetRoom> getRooms = null;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("getRates")
    @Expose
    private List<Object> getRates = null;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("minPrice")
    @Expose
    private String minPrice;
    @SerializedName("bookNowImg")
    @Expose
    private String bookNowImg;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAvailbleTickets() {
        return availbleTickets;
    }

    public void setAvailbleTickets(Integer availbleTickets) {
        this.availbleTickets = availbleTickets;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getHotelImages() {
        return hotelImages;
    }

    public void setHotelImages(List<String> hotelImages) {
        this.hotelImages = hotelImages;
    }

    public List<GetAmenity> getGetAmenities() {
        return getAmenities;
    }

    public void setGetAmenities(List<GetAmenity> getAmenities) {
        this.getAmenities = getAmenities;
    }

    public List<GetRoom> getGetRooms() {
        return getRooms;
    }

    public void setGetRooms(List<GetRoom> getRooms) {
        this.getRooms = getRooms;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public List<Object> getGetRates() {
        return getRates;
    }

    public void setGetRates(List<Object> getRates) {
        this.getRates = getRates;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getBookNowImg() {
        return bookNowImg;
    }

    public void setBookNowImg(String bookNowImg) {
        this.bookNowImg = bookNowImg;
    }

}
