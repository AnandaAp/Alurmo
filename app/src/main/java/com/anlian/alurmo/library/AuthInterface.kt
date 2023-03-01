package com.anlian.alurmo.library

import com.anlian.alurmo.models.Account
import com.anlian.alurmo.ui.states.AccountState
import com.anlian.alurmo.ui.states.AuthState

/**
 * This interface used for implementing function that used for Auth ViewModel
 */
interface AuthInterface : BaseViewModelInterface {
    fun checkAccount(account: Account)
    fun proceedAuth(
        account: Account,
        code: AuthState,
        provider: AccountState
    )
}