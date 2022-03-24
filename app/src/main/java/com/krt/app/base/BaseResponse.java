package com.krt.app.base;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class BaseResponse {
    @SerializedName("data")
    @Getter
    private String data;
}
