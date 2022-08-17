package com.example.domain.storage.preferences

import android.content.Context
import androidx.preference.PreferenceManager

class PreferenceHelper(context: Context) {

    private val pref = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        const val USER_RESPONSE_DATA = "pref_user_response_data"
        const val TOKEN_DATA = "pref_token_data"
        const val REFRESH_TOKEN_DATA = "pref_refresh_token_data"
    }

    var userResponseStr: String?
        get() = pref.getString(USER_RESPONSE_DATA, null)
        set(value) {
            pref.edit().putString(USER_RESPONSE_DATA, value).apply()
        }

    var token: String?
        get() = pref.getString(TOKEN_DATA, null)
        set(value) {
            pref.edit().putString(TOKEN_DATA, value).apply()
        }

    var refreshToken: String?
        get() = pref.getString(REFRESH_TOKEN_DATA, null)
        set(value) {
            pref.edit().putString(REFRESH_TOKEN_DATA, value).apply()
        }


}