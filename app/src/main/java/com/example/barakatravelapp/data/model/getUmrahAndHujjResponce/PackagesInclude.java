
package com.example.barakatravelapp.data.model.getUmrahAndHujjResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PackagesInclude implements Serializable {

    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("packageName")
    @Expose
    private String packageName;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}
