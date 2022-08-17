package com.example.domain.mapper

import com.example.data.model.authentication.User
import com.example.domain.network.response.authentication.UserResponse

object AuthenticationMapper {

    fun mapToUser(response: UserResponse): User {
        return User(
            id = response.id,
            sex = response.sex,
            firstName = response.firstName,
            lastName = response.lastName,
            fullName = response.fullName,
            email = response.email,
            isTrainer = response.isTrainer,
            avatar = response.avatar ?: "",
            dob = response.dob ?: "",
            phone = response.phone ?: "",
            color = response.color ?: "",
            clientConnection = response.clientConnection ?: -1,
        )
    }

}