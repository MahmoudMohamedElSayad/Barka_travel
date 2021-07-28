
package com.example.barakatravelapp.data.model.getBookingPackageResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackagePerson {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_package_id")
    @Expose
    private Integer orderPackageId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("zip_code")
    @Expose
    private String zipCode;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("passport")
    @Expose
    private String passport;
    @SerializedName("passport_image")
    @Expose
    private String passportImage;
    @SerializedName("personal_image")
    @Expose
    private String personalImage;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderPackageId() {
        return orderPackageId;
    }

    public void setOrderPackageId(Integer orderPackageId) {
        this.orderPackageId = orderPackageId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassportImage() {
        return passportImage;
    }

    public void setPassportImage(String passportImage) {
        this.passportImage = passportImage;
    }

    public String getPersonalImage() {
        return personalImage;
    }

    public void setPersonalImage(String personalImage) {
        this.personalImage = personalImage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
