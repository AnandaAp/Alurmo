package com.anlian.alurmo.library

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface BaseViewModelInterface {
    fun navigate(route: String, navController: NavHostController)
}

fun ViewModel.toast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

private val handler: CoroutineExceptionHandler
    get() = CoroutineExceptionHandler { _, throwable ->
        val TAG = "CoroutineExceptionHandler"
        Log.d(TAG, ": ${throwable.message}")
    }
val ViewModel.backgroundCoroutine: CoroutineContext
    get() = Dispatchers.IO + handler
val ViewModel.mainCoroutine: CoroutineContext
    get() = Dispatchers.Main + handler
val ViewModel.defaultCoroutine: CoroutineContext
    get() = Dispatchers.Default + handler
val ViewModel.unconfinedCoroutine: CoroutineContext
    get() = Dispatchers.Unconfined + handler