package com.example.domain.repositoryiml

import com.example.data.repository.authentication.AuthenticationRepository
import com.example.domain.network.api.authentication.AuthenticationAPI
import com.example.data.model.authentication.User
import com.example.domain.mapper.AuthenticationMapper
import com.example.domain.network.response.authentication.UserResponse
import com.example.domain.storage.preferences.PreferenceHelper
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single

class AuthenticationRepositoryImpl(private val api: AuthenticationAPI, private val preferenceHelper: PreferenceHelper) : AuthenticationRepository {

    override fun login(email: String, password: String): Single<User> {
        return api.login(email, password).map { response ->
            val user = AuthenticationMapper.mapToUser(response.user)
            preferenceHelper.userResponseStr = response.user.toJson()
            preferenceHelper.token = response.token
            preferenceHelper.refreshToken = response.refreshToken
            user
        }
    }

    override fun getProfile(): Single<User> {
        return api.getProfile().map { response ->
            val user = AuthenticationMapper.mapToUser(response.user)
            preferenceHelper.userResponseStr = response.user.toJson()
            user
        }
    }

    override fun getLocalUser(): Single<User> {
        preferenceHelper.userResponseStr?.let { userResponseStr ->
            val user = AuthenticationMapper.mapToUser(Gson().fromJson(userResponseStr,UserResponse::class.java))
            return Single.just(user)
        }?: kotlin.run {
            return Single.error(NullPointerException())
        }
    }

    override fun checkLocalToken(): Single<Boolean> {
        return Single.just(preferenceHelper.token?.isNotBlank() ?: false)
    }

    override fun forgotPassword(email: String): Completable {
        return api.forgotPassword(email)
    }
}