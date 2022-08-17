package com.example.data.model.authentication

data class User(
    val id: String = "",
    val sex: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val fullName: String = "",
    val email: String = "",
    val isTrainer: Boolean = false,
    val avatar: String = "",
    val dob: String = "",
    val phone: String = "",
    val color: String = "",
    val clientConnection: Int = -1,
)