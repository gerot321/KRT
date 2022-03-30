package com.slt.app.retrofit;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public static final String API_BASE_URL = "https://sltonline.s3.ap-southeast-1.amazonaws.com/";
    public static final String API_FREEGOIP = "https://api.freegeoip.app/";

    public static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();



    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit.Builder builderLocation =
            new Retrofit.Builder()
                    .baseUrl(API_FREEGOIP)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        ConnectionSpec.Builder obsoleteSpecBuilder = new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS);
        obsoleteSpecBuilder = obsoleteSpecBuilder.cipherSuites("TLS_DHE_DSS_WITH_AES_128_CBC_SHA");
        ConnectionSpec obsoleteSpec = obsoleteSpecBuilder.build();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder().addInterceptor(logging);
//        httpClient.connectionSpecs(Arrays.asList(obsoleteSpec));

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
//                        .header("ApiVersion", String.valueOf(BuildConfig.VERSION_CODE))
                        .header("Cache-Control","no-cache")
                        .header("Connection","keep-alive");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = builder.client(httpClient.connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build()).build();

        return retrofit.create(serviceClass);
    }

    public static <S> S createServiceDetectLocation(Class<S> serviceClass) {



        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder().addInterceptor(logging);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
//                        .header("ApiVersion", String.valueOf(BuildConfig.VERSION_CODE))
                        .header("Cache-Control","no-cache")
                        .header("Connection","keep-alive");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = builderLocation.client(httpClient.connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build()).build();

        return retrofit.create(serviceClass);
    }


    public static Retrofit retrofit() {
        return builder.build();
    }
}