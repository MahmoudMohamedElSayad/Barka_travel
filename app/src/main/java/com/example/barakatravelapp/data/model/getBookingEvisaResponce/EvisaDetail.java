
package com.example.barakatravelapp.data.model.getBookingEvisaResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EvisaDetail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("evisa_id")
    @Expose
    private Integer evisaId;
    @SerializedName("passport_type")
    @Expose
    private String passportType;
    @SerializedName("passport_photo")
    @Expose
    private String passportPhoto;
    @SerializedName("photo")
    @Expose
    private String photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEvisaId() {
        return evisaId;
    }

    public void setEvisaId(Integer evisaId) {
        this.evisaId = evisaId;
    }

    public String getPassportType() {
        return passportType;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    public String getPassportPhoto() {
        return passportPhoto;
    }

    public void setPassportPhoto(String passportPhoto) {
        this.passportPhoto = passportPhoto;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
