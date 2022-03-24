package com.krt.app.retrofit;



import com.krt.app.model.DataResponse;
import com.krt.app.model.LocationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicesAPI {
    @GET("upload/data.json")
    Call<DataResponse> getData();

    @GET("json")
    Call<LocationResponse> getLocationData(@Query("apikey") String key);
}
