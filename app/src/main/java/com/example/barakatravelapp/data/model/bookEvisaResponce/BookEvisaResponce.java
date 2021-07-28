
package com.example.barakatravelapp.data.model.bookEvisaResponce;

import com.example.barakatravelapp.data.model.getBookingEvisaResponce.Evisa;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookEvisaResponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("evisa")
    @Expose
    private BookEvisa evisa;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("totalAmount")
    @Expose
    private Double totalAmount;
    @SerializedName("fees")
    @Expose
    private Double fees;
    @SerializedName("countryType")
    @Expose
    private String countryType;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("payment")
    @Expose
    private String payment;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BookEvisa getEvisa() {
        return evisa;
    }

    public void setEvisa(BookEvisa evisa) {
        this.evisa = evisa;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public String getCountryType() {
        return countryType;
    }

    public void setCountryType(String countryType) {
        this.countryType = countryType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

}
