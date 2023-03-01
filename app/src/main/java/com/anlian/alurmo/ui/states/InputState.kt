package com.anlian.alurmo.ui.states

sealed class InputState(
    val emailState: Boolean = false,
    val passState: Boolean = false,
    val message: String = ""
) {
    object Unspecified : InputState()
    class Loading(message: String = "") : InputState()
    class Result(
        emailState: Boolean = false,
        passState: Boolean = false,
    ) : InputState(emailState = emailState, passState = passState)
}
