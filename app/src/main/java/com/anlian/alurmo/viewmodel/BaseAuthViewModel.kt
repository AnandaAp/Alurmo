package com.anlian.alurmo.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.anlian.alurmo.R
import com.anlian.alurmo.dll.Validator
import com.anlian.alurmo.library.AuthInterface
import com.anlian.alurmo.library.mainCoroutine
import com.anlian.alurmo.models.Account
import com.anlian.alurmo.ui.navigation.AuthRoute
import com.anlian.alurmo.ui.states.AccountState
import com.anlian.alurmo.ui.states.AuthState
import com.anlian.alurmo.ui.states.InputState
import com.anlian.alurmo.ui.states.StatusState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
open class BaseAuthViewModel @Inject constructor(
    private val auth: FirebaseAuth,
//    private val credentials: AuthCredential,
    @ApplicationContext private val context: Context
) : ViewModel(), AuthInterface {
    val TAG = "AuthViewModel"
    protected val _inputState = MutableStateFlow<InputState>(InputState.Unspecified)
    protected val statusState = MutableStateFlow<StatusState>(StatusState.Unspecified)
    private val _btnCLick = MutableStateFlow(false)
    val inputState = _inputState.asStateFlow()
    val btnClick = _btnCLick.asStateFlow()


    override fun checkAccount(account: Account) {
        val emailState = !Validator.isValidEmail(account.email)
        val passState = Validator.isValidPassword(account.password)
        _inputState.value = InputState.Result(emailState = emailState, passState = passState)
    }

    override fun proceedAuth(
        account: Account,
        code: AuthState,
        provider: AccountState
    ) {
        when (code) {
            AuthState.Default -> StatusState.Unspecified
            is AuthState.SignUp -> statusState.value = signUp(account, provider)
            is AuthState.SignIn -> statusState.value = signIn(account, provider)
        }
    }

    override fun navigate(route: String, navController: NavHostController) {
        viewModelScope.launch(mainCoroutine) {
            when (route) {
                AuthRoute.SignIn.route -> navController.navigate(AuthRoute.SignIn.route)
                AuthRoute.SignUp.route -> navController.navigate(AuthRoute.SignUp.route)
                else -> Unit
            }
        }
    }

    private fun signIn(account: Account, provider: AccountState): StatusState {
        var state: StatusState = StatusState.Unspecified
        when (provider) {
            AccountState.Email -> {
                state = if (auth.signInWithEmailAndPassword(
                        account.email,
                        account.password
                    ).isSuccessful
                ) StatusState
                    .Success(context.resources.getString(R.string.success_sign_in_message))
                else StatusState
                    .Failure(context.resources.getString(R.string.failed_sign_in_message))
            }

            AccountState.Google -> Unit
//                    if(auth.signInWithCredential(credentials).isSuccessful) StatusState
//                            .Success(context.resources.getString(R.string.success_sign_in_message))
//                    else StatusState
//                        .Failure(context.resources.getString(R.string.failed_sign_in_message))
            AccountState.Default -> Unit
        }
        return state
    }

    private fun signUp(account: Account, provider: AccountState): StatusState {
        var state: StatusState = StatusState.Unspecified
        when (provider) {
            AccountState.Email -> {
                state = if (auth.createUserWithEmailAndPassword(
                        account.email,
                        account.password
                    ).isSuccessful
                ) StatusState
                    .Success(context.resources.getString(R.string.success_sign_in_message))
                else StatusState
                    .Failure(context.resources.getString(R.string.failed_sign_in_message))
            }

            AccountState.Google -> Unit
//                    if(auth.signInWithCredential(credentials).isSuccessful) StatusState
//                        .Success(context.resources.getString(R.string.success_sign_in_message))
//                    else StatusState
//                        .Failure(context.resources.getString(R.string.failed_sign_in_message))
            AccountState.Default -> Unit
        }
        return state
    }

    fun changeState(state: Boolean) {
        _btnCLick.value = state
        if (!state) {
            _inputState.value = InputState.Loading("")
        } else {
            _inputState.value = InputState.Unspecified
        }
    }
}