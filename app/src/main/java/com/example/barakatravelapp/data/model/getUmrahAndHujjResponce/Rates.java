
package com.example.barakatravelapp.data.model.getUmrahAndHujjResponce;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rates implements Serializable {

    @SerializedName("umarRate")
    @Expose
    private Object umarRate;
    @SerializedName("rate")
    @Expose
    private List<Object> rate = null;

    public Object getUmarRate() {
        return umarRate;
    }

    public void setUmarRate(Object umarRate) {
        this.umarRate = umarRate;
    }

    public List<Object> getRate() {
        return rate;
    }

    public void setRate(List<Object> rate) {
        this.rate = rate;
    }

}
