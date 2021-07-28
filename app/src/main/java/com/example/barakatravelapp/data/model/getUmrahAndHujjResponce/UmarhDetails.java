
package com.example.barakatravelapp.data.model.getUmrahAndHujjResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UmarhDetails implements Serializable {

    @SerializedName("included")
    @Expose
    private String included;
    @SerializedName("notSelected")
    @Expose
    private String notSelected;
    @SerializedName("important_notes")
    @Expose
    private String importantNotes;
    @SerializedName("howToBook")
    @Expose
    private String howToBook;

    public String getIncluded() {
        return included;
    }

    public void setIncluded(String included) {
        this.included = included;
    }

    public String getNotSelected() {
        return notSelected;
    }

    public void setNotSelected(String notSelected) {
        this.notSelected = notSelected;
    }

    public String getImportantNotes() {
        return importantNotes;
    }

    public void setImportantNotes(String importantNotes) {
        this.importantNotes = importantNotes;
    }

    public String getHowToBook() {
        return howToBook;
    }

    public void setHowToBook(String howToBook) {
        this.howToBook = howToBook;
    }

}
