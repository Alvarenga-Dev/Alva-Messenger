package com.alvarengadev.alvamessenger.utils

import android.content.Context
import android.content.SharedPreferences

class PrefencesUtils(context: Context) {

    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    companion object{
        private val EMAIL_LOGIN = "emaillogin.alvarengadev.alvamessenger"
        private val PASSWORD_LOGIN = "passwordlogin.alvarengadev.alvamessenger"
        private val IS_LOGIN = "islogin.alvarengadev.alvamesseger"
        private val PREFERENCES = "preferences.alvarengadev.alvamesseger"
        private val MODE = 0
    }

    init {
        preferences = context.getSharedPreferences(PREFERENCES, MODE)
        editor = preferences.edit()
    }

    fun saveLogin (isLogin: Boolean, emailLogin: String, passwordLogin: String) {
        editor.putString(EMAIL_LOGIN, emailLogin)
        editor.putString(PASSWORD_LOGIN, passwordLogin)
        editor.commit()
    }

    fun getEmail() : String? {
        return preferences.getString(EMAIL_LOGIN, "")
    }

    fun getPassword() : String? {
        return preferences.getString(PASSWORD_LOGIN, "")
    }

    fun isLogin() : Boolean {
        return preferences.getBoolean(IS_LOGIN, false)
    }

}