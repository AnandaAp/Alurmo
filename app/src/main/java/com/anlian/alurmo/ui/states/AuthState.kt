package com.anlian.alurmo.ui.states

import com.anlian.alurmo.ui.navigation.AuthRoute

sealed class AuthState(
    val message: String = "Default",
    val route: AuthRoute = AuthRoute.WelcomePage
) {
    object Default : AuthState()
    class SignIn(
        message: String,
        route: AuthRoute
    ) : AuthState(message = message, route = route)

    class SignUp(
        message: String,
        route: AuthRoute
    ) : AuthState(message = message, route = route)
}
