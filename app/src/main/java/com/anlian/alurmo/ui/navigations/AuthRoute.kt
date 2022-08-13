package com.anlian.alurmo.ui.navigation

sealed class AuthRoute(val route: String = "com.anlian.alurmo.view.welcome") {
    object WelcomePage: AuthRoute()
    object SignUp: AuthRoute(route = "com.anlian.alurmo.view.sign.up")
    object SignIn: AuthRoute(route = "com.anlian.alurmo.view.sign.in")
    object SuccessSignInSplashScreen :AuthRoute(route = "com.anlian.alurmo.view.sign.in.success")
}