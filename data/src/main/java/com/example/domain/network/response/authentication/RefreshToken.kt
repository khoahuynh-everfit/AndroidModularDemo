package com.example.domain.network.response.authentication

import com.google.gson.annotations.SerializedName

data class RefreshToken(
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("token") val token: String,
    @SerializedName("token_expiry") val tokenExpiry: Long
)