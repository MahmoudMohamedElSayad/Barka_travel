
package com.example.barakatravelapp.data.model.getBookingEvisaResponce;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EVisaDate {

    @SerializedName("evisa")
    @Expose
    private Evisa evisa;
    @SerializedName("evisaDetail")
    @Expose
    private List<EvisaDetail> evisaDetail = null;
    @SerializedName("completePayment")
    @Expose
    private String completePayment;

    public Evisa getEvisa() {
        return evisa;
    }

    public void setEvisa(Evisa evisa) {
        this.evisa = evisa;
    }

    public List<EvisaDetail> getEvisaDetail() {
        return evisaDetail;
    }

    public void setEvisaDetail(List<EvisaDetail> evisaDetail) {
        this.evisaDetail = evisaDetail;
    }

    public String getCompletePayment() {
        return completePayment;
    }

    public void setCompletePayment(String completePayment) {
        this.completePayment = completePayment;
    }

}
