
package com.example.barakatravelapp.data.model.getBookingPackageResponce;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBookingPackageResponce {

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
    private List<BookingPackage> _package = null;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<BookingPackage> getPackage() {
        return _package;
    }

    public void setPackage(List<BookingPackage> _package) {
        this._package = _package;
    }

}
