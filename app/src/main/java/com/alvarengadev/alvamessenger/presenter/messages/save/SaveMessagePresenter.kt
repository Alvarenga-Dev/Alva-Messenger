package com.alvarengadev.alvamessenger.presenter.messages.save

import android.content.Context
import com.alvarengadev.alvamessenger.data.domain.Message
import com.alvarengadev.alvamessenger.data.firebase.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import java.lang.Exception

class SaveMessagePresenter(private val context: Context) {

    fun save(sender: String, receiver: String?, message: String): Boolean {

        val preferences = PreferencesUtils(context)
        val messages = Message(
            preferences.getUserKey()!!,
            message
        )

        return try {
            SettingsFirebase.databaseReference
                .child("Messages")
                .child(sender)
                .child(receiver!!)
                .push()
                .setValue(messages)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}
