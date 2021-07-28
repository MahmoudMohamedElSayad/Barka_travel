
package com.example.barakatravelapp.data.model.getBookingPackageResponce;

import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.GetTopUmarAndTophajjPackage;
import com.example.barakatravelapp.data.model.getUmrahAndHujjResponce.Umar;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingPackage {

    @SerializedName("packageDetail")
    @Expose
    private GetTopUmarAndTophajjPackage packageDetail;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("departure_date")
    @Expose
    private String departureDate;
    @SerializedName("return_date")
    @Expose
    private String returnDate;
    @SerializedName("prief_travel")
    @Expose
    private String priefTravel;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("paid")
    @Expose
    private Integer paid;
    @SerializedName("remaining")
    @Expose
    private Integer remaining;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("package_pricing_id")
    @Expose
    private Integer packagePricingId;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("contact_number")
    @Expose
    private String contactNumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("city_code")
    @Expose
    private String cityCode;
    @SerializedName("packagePerson")
    @Expose
    private PackagePerson packagePerson;

    public GetTopUmarAndTophajjPackage getPackageDetail() {
        return packageDetail;
    }

    public void setPackageDetail(GetTopUmarAndTophajjPackage packageDetail) {
        this.packageDetail = packageDetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getPriefTravel() {
        return priefTravel;
    }

    public void setPriefTravel(String priefTravel) {
        this.priefTravel = priefTravel;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPackagePricingId() {
        return packagePricingId;
    }

    public void setPackagePricingId(Integer packagePricingId) {
        this.packagePricingId = packagePricingId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public PackagePerson getPackagePerson() {
        return packagePerson;
    }

    public void setPackagePerson(PackagePerson packagePerson) {
        this.packagePerson = packagePerson;
    }

}
