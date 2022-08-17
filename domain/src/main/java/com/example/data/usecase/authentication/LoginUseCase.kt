package com.example.data.usecase.authentication

import com.example.data.model.authentication.User
import com.example.data.repository.authentication.AuthenticationRepository
import io.reactivex.Single
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthenticationRepository) {

    fun execute(email: String, password: String) : Single<User> {
        return repository.login(email, password)
    }

}