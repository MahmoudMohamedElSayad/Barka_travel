
package com.example.barakatravelapp.data.model.userLoginResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLoginGeneralResponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("paypal")
    @Expose
    private String paypal;
    @SerializedName("user")
    @Expose
    private UserData user;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getPaypal() {
        return paypal;
    }

    public void setPaypal(String paypal) {
        this.paypal = paypal;
    }


    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

}
