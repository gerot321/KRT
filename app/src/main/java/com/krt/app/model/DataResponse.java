package com.krt.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class DataResponse implements Parcelable {
    @SerializedName("imagePath")
    @Getter @Setter
    private String imagePath;
    @SerializedName("offIndicator")
    @Getter @Setter
    private boolean offIndicator;
    @SerializedName("data")
    @Getter @Setter
    private List<Game> data;

    public DataResponse(){

    }

    protected DataResponse(Parcel in) {
        imagePath = in.readString();
        offIndicator = in.readByte() != 0;
        data = in.createTypedArrayList(Game.CREATOR);
    }

    public static final Creator<DataResponse> CREATOR = new Creator<DataResponse>() {
        @Override
        public DataResponse createFromParcel(Parcel in) {
            return new DataResponse(in);
        }

        @Override
        public DataResponse[] newArray(int size) {
            return new DataResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imagePath);
        dest.writeByte((byte) (offIndicator ? 1 : 0));
        dest.writeTypedList(data);
    }
}
