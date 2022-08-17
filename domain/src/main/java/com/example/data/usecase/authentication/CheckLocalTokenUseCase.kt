package com.example.data.usecase.authentication

import com.example.data.repository.authentication.AuthenticationRepository
import io.reactivex.Single
import javax.inject.Inject

class CheckLocalTokenUseCase @Inject constructor(private val repository: AuthenticationRepository) {

    fun execute() : Single<Boolean> {
        return repository.checkLocalToken()
    }

}