package com.alvarengadev.alvamessenger.presenter.chat.save

import com.alvarengadev.alvamessenger.data.domain.Chat
import com.alvarengadev.alvamessenger.data.firebase.settings.SettingsFirebase

class SaveChatPresenter {

    fun save(sender: String, receiver: String?, chat: Chat): Boolean {

        return try {
            SettingsFirebase.databaseReference
                .child("Chats")
                .child(sender)
                .child(receiver!!)
                .setValue(chat)
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }
}