package com.example.restapidemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API_Interface {

    @GET("json/utc/now")
    Call<CallingAPI>getUTCtime();
    @GET("json/est/now")
    Call<CallingAPI>getESTtime();

   /* @FormUrlEncoded
    @POST("sign_in")
    Call<CallingAPI> signIn(@Field("email") String email);*/
}
