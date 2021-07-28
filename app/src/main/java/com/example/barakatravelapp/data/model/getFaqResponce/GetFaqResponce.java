
package com.example.barakatravelapp.data.model.getFaqResponce;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetFaqResponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("faq")
    @Expose
    private List<Faq> faq = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public List<Faq> getFaq() {
        return faq;
    }

    public void setFaq(List<Faq> faq) {
        this.faq = faq;
    }

}
