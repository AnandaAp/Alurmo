package com.anlian.alurmo.ui.states

sealed class StatusState(val message: String = "Unspecified") {
    object Unspecified : StatusState()
    class Success(message: String = "") : StatusState(message = message)
    class Failure(message: String = "") : StatusState(message = message)
}
