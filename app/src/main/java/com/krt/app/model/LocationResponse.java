
package com.krt.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class LocationResponse implements Parcelable{
    @Getter @Setter
    private String country_code;
    @Getter @Setter
    private String country_name;
    @Getter @Setter
    private String region_code;
    @Getter @Setter
    private String region_name;
    @Getter @Setter
    private String city;
    @Getter @Setter
    private String zip_code;
    @Getter @Setter
    private String time_zone;

    protected LocationResponse(Parcel in) {
        country_code = in.readString();
        country_name = in.readString();
        region_code = in.readString();
        region_name = in.readString();
        city = in.readString();
        zip_code = in.readString();
        time_zone = in.readString();
    }

    public static final Creator<LocationResponse> CREATOR = new Creator<LocationResponse>() {
        @Override
        public LocationResponse createFromParcel(Parcel in) {
            return new LocationResponse(in);
        }

        @Override
        public LocationResponse[] newArray(int size) {
            return new LocationResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(country_code);
        parcel.writeString(country_name);
        parcel.writeString(region_code);
        parcel.writeString(region_name);
        parcel.writeString(city);
        parcel.writeString(zip_code);
        parcel.writeString(time_zone);
    }
}
