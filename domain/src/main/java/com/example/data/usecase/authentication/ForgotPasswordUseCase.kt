package com.example.data.usecase.authentication

import com.example.data.repository.authentication.AuthenticationRepository
import io.reactivex.Completable
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(private val repository: AuthenticationRepository) {

    fun execute(email: String): Completable {
        return repository.forgotPassword(email)
    }

}