package com.example.siqasir_lanjutan.rest;

import com.example.siqasir_lanjutan.response.ResponseLogin;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> login (
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponseBody> signup (
            @Field("username") String username,
            @Field("password") String password,
            @Field("nama_lengkap") String namaLengkap,
            @Field("email") String email,
            @Field("no_hp") String nomorHP
    );
}
