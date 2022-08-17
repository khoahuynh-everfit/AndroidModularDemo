package com.example.data.repository.authentication

import com.example.data.model.authentication.User
import io.reactivex.Completable
import io.reactivex.Single

interface AuthenticationRepository {

    fun login(email: String, password: String): Single<User>

    fun getProfile(): Single<User>

    fun getLocalUser(): Single<User>

    fun checkLocalToken(): Single<Boolean>

    fun forgotPassword(email: String): Completable

}