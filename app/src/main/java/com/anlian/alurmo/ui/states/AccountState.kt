package com.anlian.alurmo.ui.states

sealed class AccountState {
    object Default : AccountState()
    object Email : AccountState()
    object Google : AccountState()
}