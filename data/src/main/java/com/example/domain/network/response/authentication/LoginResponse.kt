package com.example.domain.network.response.authentication

import com.example.domain.network.response.BaseResponse
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") val token: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("firebase_token") val firebaseToken: String,
    @SerializedName("user") val user: UserResponse
) : BaseResponse()