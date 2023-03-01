package com.anlian.alurmo.ui.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anlian.alurmo.R
import com.anlian.alurmo.ui.navigation.AuthRoute
import com.anlian.alurmo.ui.states.AuthState
import com.anlian.alurmo.ui.view.SignIn
import com.anlian.alurmo.ui.view.SignInSplashScreen
import com.anlian.alurmo.ui.view.SignUp
import com.anlian.alurmo.ui.view.WelcomePage

@Composable
fun AuthNavigation(navController: NavHostController = rememberNavController()) {
    val welcomeRoute = AuthRoute.WelcomePage
    val signInRoute = AuthRoute.SignIn
    val signUpRoute = AuthRoute.SignUp
    val successSignIn = AuthRoute.SuccessSignInSplashScreen
    NavHost(
        navController = navController,
        startDestination = welcomeRoute.route
    ) {
        composable(route = welcomeRoute.route) {
            WelcomePage(navController)
        }
        composable(route = signInRoute.route) {
            val state = AuthState.SignIn(
                message = stringResource(id = R.string.sign_in_label),
                route = signInRoute
            )
            SignIn(navController, state)
        }
        composable(route = signUpRoute.route) {
            val state = AuthState.SignUp(
                message = stringResource(id = R.string.sign_up_label),
                route = signUpRoute
            )
            SignUp(navController, state)
        }
        composable(route = successSignIn.route) {
            SignInSplashScreen()
        }
    }
}