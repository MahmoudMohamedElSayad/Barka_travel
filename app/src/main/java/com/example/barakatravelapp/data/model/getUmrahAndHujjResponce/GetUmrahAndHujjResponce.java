
package com.example.barakatravelapp.data.model.getUmrahAndHujjResponce;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUmrahAndHujjResponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("package")
    @Expose
    private List<GetTopUmarAndTophajjPackage> hajjAndUmrahPackage = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<GetTopUmarAndTophajjPackage> getPackage() {
        return hajjAndUmrahPackage;
    }

    public void setPackage(List<GetTopUmarAndTophajjPackage> _package) {
        this.hajjAndUmrahPackage = _package;
    }

}
