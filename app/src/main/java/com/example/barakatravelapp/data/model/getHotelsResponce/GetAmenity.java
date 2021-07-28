
package com.example.barakatravelapp.data.model.getHotelsResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetAmenity implements Serializable {
// وسائل الراحه amenities
    @SerializedName("amenitiesId")
    @Expose
    private Integer amenitiesId;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getAmenitiesId() {
        return amenitiesId;
    }

    public void setAmenitiesId(Integer amenitiesId) {
        this.amenitiesId = amenitiesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
