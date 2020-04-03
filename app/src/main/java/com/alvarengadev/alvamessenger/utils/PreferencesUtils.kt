package com.alvarengadev.alvamessenger.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesUtils(context: Context) {

    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    companion object{
        private const val USER_KEY = "idUserLogin"
        private const val PREFERENCES = "preferences.alvamesseger"
        private const val MODE = 0
    }

    init {
        preferences = context.getSharedPreferences(PREFERENCES, MODE)
        editor = preferences.edit()
    }

    fun saveUserDatas (userKey: String) {
        editor.putString(USER_KEY, userKey)
        editor.commit()
    }

    fun getUserDatas(): String? {
        return preferences.getString(USER_KEY, null)
    }

}