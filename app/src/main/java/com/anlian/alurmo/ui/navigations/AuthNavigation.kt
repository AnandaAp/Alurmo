package com.anlian.alurmo.ui.navigations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anlian.alurmo.ui.navigation.AuthRoute
import com.anlian.alurmo.ui.view.SignIn
import com.anlian.alurmo.ui.view.SignInSplashScreen
import com.anlian.alurmo.ui.view.SignUp
import com.anlian.alurmo.ui.view.WelcomePage
import com.anlian.alurmo.viewmodel.WelcomeViewModel

@Composable
fun AuthNavigation(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = AuthRoute.WelcomePage.route,
    ){
        composable(route = AuthRoute.WelcomePage.route){
            WelcomePage(navController)
        }
        composable(route = AuthRoute.SignIn.route) {
            SignIn()
        }
        composable(route = AuthRoute.SignUp.route){
            SignUp(navController)
        }
        composable(route = AuthRoute.SuccessSignInSplashScreen.route){
            SignInSplashScreen()
        }
    }
}