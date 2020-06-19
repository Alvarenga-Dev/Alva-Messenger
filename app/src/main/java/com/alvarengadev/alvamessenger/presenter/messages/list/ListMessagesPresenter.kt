package com.alvarengadev.alvamessenger.presenter.messages.list

import com.alvarengadev.alvamessenger.data.domain.Message
import com.alvarengadev.alvamessenger.data.firebase.SettingsFirebase
import com.alvarengadev.alvamessenger.view.adapters.messages.ListMessagesAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ListMessagesPresenter(private val view: ListMessagesInterface.View) :
    ListMessagesInterface.Presenter,
    ValueEventListener {

    private val databaseReference = SettingsFirebase.databaseReference
    private val arrayMessages = ArrayList<Message>()
    private val listChatAdapter = ListMessagesAdapter(arrayMessages)

    override fun stopGetMessages() = databaseReference.removeEventListener(this)

    override fun getAdapter(): ListMessagesAdapter {
        databaseReference.child("Messages")
            .child(view.userKey()!!)
            .child(view.friendKey()!!)
            .addValueEventListener(this)

        return listChatAdapter
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {

        arrayMessages.clear()

        for (data in dataSnapshot.children) {
            val message = data.getValue(Message::class.java)!!
            arrayMessages.add(message)
        }

        listChatAdapter.notifyDataSetChanged()
    }

    override fun onCancelled(databaseError: DatabaseError) =
        view.error("Erro na troca de mensagens!")

}
