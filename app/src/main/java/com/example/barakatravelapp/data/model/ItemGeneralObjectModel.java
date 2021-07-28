package com.example.barakatravelapp.data.model;

public class ItemGeneralObjectModel {


    private String photoPath;
    private String passportName;
    private String passportPrice;
    private String passportvalidaty;
    private String passportVisaTime;
    private String name;
    private int photo;
    private int passportPhoto;

    private String quesion;
    private String answer;

    public ItemGeneralObjectModel(String quesion, String answer) {
        this.quesion = quesion;
        this.answer = answer;
    }

    public ItemGeneralObjectModel(String name, String photoPath,int photo) {
        this.name = name;
        this.photoPath = photoPath;
    }

    public ItemGeneralObjectModel(String name, int photo) {
        this.name = name;
        this.photo = photo;
    }
    public ItemGeneralObjectModel(String name) {
        this.name = name;
    }
    public ItemGeneralObjectModel(String passportName, String passportPrice, String passportvalidaty, String passportVisaTime, int passportPhoto, int photo) {
        this.passportName = passportName;
        this.passportPrice = passportPrice;
        this.passportvalidaty = passportvalidaty;
        this.passportVisaTime = passportVisaTime;
        this.photo = photo;
        this.passportPhoto = passportPhoto;

    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPassportName() {
        return passportName;
    }

    public void setPassportName(String passportName) {
        this.passportName = passportName;
    }

    public String getPassportPrice() {
        return passportPrice;
    }

    public void setPassportPrice(String passportPrice) {
        this.passportPrice = passportPrice;
    }

    public String getPassportvalidaty() {
        return passportvalidaty;
    }

    public void setPassportvalidaty(String passportvalidaty) {
        this.passportvalidaty = passportvalidaty;
    }

    public String getPassportVisaTime() {
        return passportVisaTime;
    }

    public void setPassportVisaTime(String passportVisaTime) {
        this.passportVisaTime = passportVisaTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getPassportPhoto() {
        return passportPhoto;
    }

    public void setPassportPhoto(int passportPhoto) {
        this.passportPhoto = passportPhoto;
    }

    public String getQuesion() {
        return quesion;
    }

    public void setQuesion(String quesion) {
        this.quesion = quesion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}