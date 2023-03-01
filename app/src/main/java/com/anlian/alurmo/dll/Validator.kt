package com.anlian.alurmo.dll

import android.util.Log
import android.util.Patterns

object Validator {
    private val TAG = "Validator"
    fun isValidPassword(pass: String): Boolean {
        Log.d(TAG, "isValidPassword: ${pass.length >= 8}")
        return pass.length < 8
    }

    fun isValidEmail(email: String): Boolean {
        Log.d(TAG, "isValidEmail: ${Patterns.EMAIL_ADDRESS.matcher(email).matches()}")
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}