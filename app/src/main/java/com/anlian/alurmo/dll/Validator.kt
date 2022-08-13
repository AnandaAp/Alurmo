package com.anlian.alurmo.dll

object Validator {
    fun passwordValidator(pass: String): Boolean{
        return pass.length > 8
    }
}