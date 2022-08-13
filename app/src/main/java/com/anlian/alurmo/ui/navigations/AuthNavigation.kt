package com.anlian.alurmo.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anlian.alurmo.ui.navigation.AuthRoute
import com.anlian.alurmo.ui.view.SignIn
import com.anlian.alurmo.ui.view.SignInSplashScreen
import com.anlian.alurmo.ui.view.SignUp
import com.anlian.alurmo.ui.view.WelcomePage

@Composable
fun AuthNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = AuthRoute.WelcomePage.route,
    ){
        composable(route = AuthRoute.WelcomePage.route){
            WelcomePage()
        }
        composable(route = AuthRoute.SignIn.route) {
            SignIn()
        }
        composable(route = AuthRoute.SignUp.route){
            SignUp()
        }
        composable(route = AuthRoute.SuccessSignInSplashScreen.route){
            SignInSplashScreen()
        }
    }
}