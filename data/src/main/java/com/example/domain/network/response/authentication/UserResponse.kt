package com.example.domain.network.response.authentication

import com.example.domain.network.response.BaseResponse
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("sex") val sex: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("email") val email: String,
    @SerializedName("is_trainer") val isTrainer: Boolean,
    @SerializedName("avatar") val avatar: String?,
    @SerializedName("dob") val dob: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("color") val color: String?,
    @SerializedName("client_connection") val clientConnection: Int?
) : BaseResponse()