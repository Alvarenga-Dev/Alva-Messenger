package com.alvarengadev.alvamessenger.presenter.messages

import android.content.Context
import com.alvarengadev.alvamessenger.data.domain.Message
import com.alvarengadev.alvamessenger.data.firebase.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.Base64Actions
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.alvarengadev.alvamessenger.view.adapters.messages.ListMessagesAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class GetMessages(private val context: Context) : ValueEventListener {

    private val databaseReference = SettingsFirebase.databaseReference
    private val arrayMessages = ArrayList<Message>()
    private val listChatAdapter = ListMessagesAdapter(arrayMessages)

    fun get(idFriend: String?): ListMessagesAdapter {
        val preferences = PreferencesUtils(context)

        databaseReference.child("Messages")
            .child(preferences.getUserKey()!!)
            .child(Base64Actions.encodeBase64(idFriend!!))
            .addValueEventListener(this)

        return listChatAdapter
    }

    fun getStop() {
        databaseReference.removeEventListener(this)
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {

        arrayMessages.clear()

        for (datas in dataSnapshot.children) {
            val message = datas.getValue(Message::class.java)
            arrayMessages.add(message!!)
        }

        listChatAdapter.notifyDataSetChanged()

    }

    override fun onCancelled(databaseError: DatabaseError) {
    }

}
