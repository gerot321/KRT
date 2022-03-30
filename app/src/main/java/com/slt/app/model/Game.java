package com.slt.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class Game implements Parcelable {
    @SerializedName("webUrl")
    @Getter @Setter
    private String webUrl;
    @SerializedName("name")
    @Getter @Setter
    private String name;
    @SerializedName("imageName")
    @Getter @Setter
    private String imageName;

    public Game(){

    }

    protected Game(Parcel in) {
        webUrl = in.readString();
        name = in.readString();
        imageName = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(webUrl);
        dest.writeString(name);
        dest.writeString(imageName);
    }
}
