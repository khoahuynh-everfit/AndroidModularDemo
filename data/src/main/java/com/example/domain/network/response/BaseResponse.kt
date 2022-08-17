package com.example.domain.network.response

import com.google.gson.Gson
import java.io.Serializable

open class BaseResponse : Serializable {

    fun toJson() : String {
        return Gson().toJson(this)
    }

}