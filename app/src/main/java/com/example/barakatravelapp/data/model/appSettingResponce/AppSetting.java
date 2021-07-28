
package com.example.barakatravelapp.data.model.appSettingResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AppSetting implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("facebook_link")
    @Expose
    private String facebookLink;
    @SerializedName("twitter_link")
    @Expose
    private String twitterLink;
    @SerializedName("youtube_link")
    @Expose
    private String youtubeLink;
    @SerializedName("google_plus_link")
    @Expose
    private String googlePlusLink;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("phone1")
    @Expose
    private String phone1;
    @SerializedName("phone2")
    @Expose
    private String phone2;
    @SerializedName("linkedin_link")
    @Expose
    private String linkedinLink;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("about_us")
    @Expose
    private String aboutUs;
    @SerializedName("about_us_description")
    @Expose
    private String aboutUsDescription;
    @SerializedName("home_top_umarh")
    @Expose
    private String homeTopUmarh;
    @SerializedName("home_top_hajj")
    @Expose
    private String homeTopHajj;
    @SerializedName("home_top_stopOver")
    @Expose
    private String homeTopStopOver;
    @SerializedName("home_top_visit")
    @Expose
    private String homeTopVisit;
    @SerializedName("home_top_hotels")
    @Expose
    private String homeTopHotels;
    @SerializedName("home_top_flights")
    @Expose
    private String homeTopFlights;
    @SerializedName("policy_terms")
    @Expose
    private String policyTerms;
    @SerializedName("location_google_map")
    @Expose
    private String locationGoogleMap;
    @SerializedName("contact_us_description")
    @Expose
    private String contactUsDescription;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("emailwebsite")
    @Expose
    private String emailwebsite;
    @SerializedName("cover_title")
    @Expose
    private String coverTitle;
    @SerializedName("cover_description")
    @Expose
    private String coverDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getGooglePlusLink() {
        return googlePlusLink;
    }

    public void setGooglePlusLink(String googlePlusLink) {
        this.googlePlusLink = googlePlusLink;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getAboutUsDescription() {
        return aboutUsDescription;
    }

    public void setAboutUsDescription(String aboutUsDescription) {
        this.aboutUsDescription = aboutUsDescription;
    }

    public String getHomeTopUmarh() {
        return homeTopUmarh;
    }

    public void setHomeTopUmarh(String homeTopUmarh) {
        this.homeTopUmarh = homeTopUmarh;
    }

    public String getHomeTopHajj() {
        return homeTopHajj;
    }

    public void setHomeTopHajj(String homeTopHajj) {
        this.homeTopHajj = homeTopHajj;
    }

    public String getHomeTopStopOver() {
        return homeTopStopOver;
    }

    public void setHomeTopStopOver(String homeTopStopOver) {
        this.homeTopStopOver = homeTopStopOver;
    }

    public String getHomeTopVisit() {
        return homeTopVisit;
    }

    public void setHomeTopVisit(String homeTopVisit) {
        this.homeTopVisit = homeTopVisit;
    }

    public String getHomeTopHotels() {
        return homeTopHotels;
    }

    public void setHomeTopHotels(String homeTopHotels) {
        this.homeTopHotels = homeTopHotels;
    }

    public String getHomeTopFlights() {
        return homeTopFlights;
    }

    public void setHomeTopFlights(String homeTopFlights) {
        this.homeTopFlights = homeTopFlights;
    }

    public String getPolicyTerms() {
        return policyTerms;
    }

    public void setPolicyTerms(String policyTerms) {
        this.policyTerms = policyTerms;
    }

    public String getLocationGoogleMap() {
        return locationGoogleMap;
    }

    public void setLocationGoogleMap(String locationGoogleMap) {
        this.locationGoogleMap = locationGoogleMap;
    }

    public String getContactUsDescription() {
        return contactUsDescription;
    }

    public void setContactUsDescription(String contactUsDescription) {
        this.contactUsDescription = contactUsDescription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailwebsite() {
        return emailwebsite;
    }

    public void setEmailwebsite(String emailwebsite) {
        this.emailwebsite = emailwebsite;
    }

    public String getCoverTitle() {
        return coverTitle;
    }

    public void setCoverTitle(String coverTitle) {
        this.coverTitle = coverTitle;
    }

    public String getCoverDescription() {
        return coverDescription;
    }

    public void setCoverDescription(String coverDescription) {
        this.coverDescription = coverDescription;
    }

}
