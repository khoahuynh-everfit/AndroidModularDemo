package com.example.component.constants

object ClientConstants {

    enum class ClientConnection(val value: Int) {
        ARCHIVED(-2),
        OFFLINE(-1),
        PENDING(0),
        CONNECTED(1),
        WAITING_ACTIVATION(2)
    }

}