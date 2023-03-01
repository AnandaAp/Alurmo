package com.anlian.alurmo.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.viewModelScope
import com.anlian.alurmo.R
import com.anlian.alurmo.library.backgroundCoroutine
import com.anlian.alurmo.models.Account
import com.anlian.alurmo.ui.states.AccountState
import com.anlian.alurmo.ui.states.AuthState
import com.anlian.alurmo.ui.states.InputState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class SignUpViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val auth: FirebaseAuth,
//    private val credentials: AuthCredential
) : BaseAuthViewModel(auth, /*credentials,*/ context) {
    val signUpState = statusState.asStateFlow()

    override fun checkAccount(account: Account) {
        viewModelScope.launch(backgroundCoroutine) {
            _inputState.value = InputState
                .Loading(context.resources.getString(R.string.loading_message))
            super.checkAccount(account)
        }
    }

    override fun proceedAuth(
        account: Account,
        code: AuthState,
        provider: AccountState
    ) {
        checkAccount(account)
//        viewModelScope.launch(backgroundCoroutine){
//            when(inputState.value){
//                is InputState.Result -> {
//                    if(inputState.value.emailState && inputState.value.passState){
//                        super.proceedAuth(account, code, provider)
//                        toast(signUpState.value.message,context)
//                    } else {
//                        _inputState.value = InputState.Unspecified
//                    }
//                }
//                else -> Unit
//            }
//        }
    }


}