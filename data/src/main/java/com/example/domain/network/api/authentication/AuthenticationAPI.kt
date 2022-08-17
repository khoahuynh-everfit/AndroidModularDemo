package com.example.domain.network.api.authentication

import com.example.domain.network.response.authentication.LoginResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface AuthenticationAPI {

    @FormUrlEncoded
    @POST("auth/login")
    fun login(@Field("email") email: String, @Field("password") password: String): Single<LoginResponse>

    @GET("profile")
    fun getProfile(): Single<LoginResponse>

    @FormUrlEncoded
    @POST("auth/forgot")
    fun forgotPassword(@Field("email") email: String): Completable

}