package com.anlian.alurmo.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class SignInViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val auth: FirebaseAuth,
//    private val credentials: AuthCredential
) : BaseAuthViewModel(auth, /*credentials,*/ context) {
    val signInState = statusState.asStateFlow()
}