package com.rsz.foodapp.network

import com.rsz.foodapp.model.response.Wrapper
import com.rsz.foodapp.model.response.home.HomeResponse
import com.rsz.foodapp.model.response.login.LoginResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface Endpoint {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<Wrapper<LoginResponse>>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
    ): Observable<Wrapper<LoginResponse>>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(
        @Part profileImage: MultipartBody.Part,
    ): Observable<Wrapper<Any>>

    @GET("food")
    fun home(): Observable<Wrapper<HomeResponse>>
}