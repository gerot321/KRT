package com.slt.app.retrofit;



import com.slt.app.model.DataResponse;
import com.slt.app.model.LocationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicesAPI {
    @GET("json-file/data-slot.json")
    Call<DataResponse> getData();

    @GET("json")
    Call<LocationResponse> getLocationData(@Query("apikey") String key);
}
