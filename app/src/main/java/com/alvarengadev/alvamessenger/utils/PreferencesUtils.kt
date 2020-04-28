package com.alvarengadev.alvamessenger.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesUtils(context: Context) {

    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    companion object{
        private const val USER_KEY = "userId"
        private const val USER_NAME = "userName"
        private const val PREFERENCES = "preferences.alvamesseger"
        private const val MODE = 0
    }

    init {
        preferences = context.getSharedPreferences(PREFERENCES, MODE)
        editor = preferences.edit()
    }

    fun saveUserDatas(userKey: String, userName: String) {
        editor.putString(USER_KEY, userKey)
        editor.putString(USER_NAME, userName)
        editor.commit()
    }

    fun getUserKey(): String? {
        return preferences.getString(USER_KEY, null)
    }

    fun getUserName(): String? {
        return preferences.getString(USER_NAME, null)
    }

}