package com.slt.app.presenter;

import android.util.Log;

import com.slt.app.SplashActivity;
import com.slt.app.base.BasePresenter;
import com.slt.app.model.DataResponse;
import com.slt.app.model.LocationResponse;
import com.slt.app.retrofit.ServiceGenerator;
import com.slt.app.retrofit.ServicesAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPresenter extends BasePresenter {
    private SplashActivity context;

    public DataPresenter(SplashActivity context) {
        this.context = context;
    }

    public void getData() {
        Call<DataResponse>  call = ServiceGenerator.createService(ServicesAPI.class).getData();;
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.body() != null) {
                    context.onResponse(response.body());
                    Log.d("update", "success");
                } else {
                    Log.d("update", "fail");
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.d("update", "fail");
            }
        });
    }

    public void getLocation() {
        Call<LocationResponse>  call = ServiceGenerator.createServiceDetectLocation(ServicesAPI.class).getLocationData("074ff9c0-5e22-11ec-8f37-ad183200ec0e");
        call.enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                if (response.body() != null) {
                    context.onGetLocation(response.body());
                    Log.d("update", "success");
                } else {
                    Log.d("update", "fail");
                }
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                Log.d("update", "fail");
            }
        });
    }
}
