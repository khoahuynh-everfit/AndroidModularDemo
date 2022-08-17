package com.example.data.usecase.authentication

import com.example.data.model.authentication.User
import com.example.data.repository.authentication.AuthenticationRepository
import io.reactivex.Single
import javax.inject.Inject

class GetLocalUserUseCase @Inject constructor(private val repository: AuthenticationRepository) {

    fun execute(): Single<User> {
        return repository.getLocalUser()
    }
}