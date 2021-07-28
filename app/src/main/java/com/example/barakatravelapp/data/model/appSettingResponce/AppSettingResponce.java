
package com.example.barakatravelapp.data.model.appSettingResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppSettingResponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("appSetting")
    @Expose
    private AppSetting appSetting;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppSetting getAppSetting() {
        return appSetting;
    }

    public void setAppSetting(AppSetting appSetting) {
        this.appSetting = appSetting;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
