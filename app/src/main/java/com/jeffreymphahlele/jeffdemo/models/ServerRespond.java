package com.jeffreymphahlele.jeffdemo.models;
import com.google.gson.annotations.SerializedName;
import com.jeffreymphahlele.jeffdemo.models.Contents;

public class ServerRespond {

    @SerializedName("photos")
    private Contents contents;

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }
}
