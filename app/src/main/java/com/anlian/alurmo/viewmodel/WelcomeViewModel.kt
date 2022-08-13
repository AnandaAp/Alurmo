package com.anlian.alurmo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.anlian.alurmo.ui.navigation.AuthRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(

): ViewModel() {
    private val TAG = "WelcomeViewModel"
    private val handler = CoroutineExceptionHandler { _, throwable ->
        Log.d(TAG, ": ${throwable.cause}")
    }

    fun navigate(
        route: String,
        navController: NavHostController
    ){
        viewModelScope.launch(Dispatchers.Main + handler) {
            when(route){
                AuthRoute.SignIn.route -> navController.navigate(AuthRoute.SignIn.route)
                AuthRoute.SignUp.route -> navController.navigate(AuthRoute.SignUp.route)
                else -> Unit
            }
        }
    }
}