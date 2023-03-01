package com.anlian.alurmo.dll

import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.anlian.alurmo.models.Account
import com.anlian.alurmo.ui.states.AccountState
import com.anlian.alurmo.ui.states.AuthState
import com.anlian.alurmo.viewmodel.BaseAuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException

private const val TAG = "GoogleAuth"

@Composable
fun registerGoogleActivityResultLauncher(
    viewModel: BaseAuthViewModel,
    account: Account,
    code: AuthState,
    provider: AccountState
)
        : ManagedActivityResultLauncher<Intent, ActivityResult> = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult()
) {
    val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
    try {
        val user = task.getResult(ApiException::class.java)!!
        viewModel.proceedAuth(
            account = account,
            code = code,
            provider = provider
        )
    } catch (e: ApiException) {
        Log.w(TAG, "Google sign in failed", e)
    }
}
