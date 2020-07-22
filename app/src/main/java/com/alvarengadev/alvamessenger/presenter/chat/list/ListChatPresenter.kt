package com.alvarengadev.alvamessenger.presenter.chat.list


import com.alvarengadev.alvamessenger.data.domain.Chat
import com.alvarengadev.alvamessenger.data.firebase.settings.SettingsFirebase
import com.alvarengadev.alvamessenger.view.adapters.chat.ListChatsAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ListChatPresenter(private val view: ListChatInterface.View) : ValueEventListener,
    ListChatInterface.Presenter {

    private val database = SettingsFirebase.databaseReference
    private val arrayChats = ArrayList<Chat>()
    private val listChatsAdapter = ListChatsAdapter(arrayChats)

    override fun getAdapter(): ListChatsAdapter {
        database.child("Chats")
            .child(view.userKey()!!)
            .addValueEventListener(this)

        return listChatsAdapter
    }

    override fun stopGetChats() = database.removeEventListener(this)

    override fun onDataChange(dataSnapshot: DataSnapshot) {

        arrayChats.clear()

        for (data in dataSnapshot.children) {
            val chat = data.getValue(Chat::class.java)!!
            arrayChats.add(chat)
        }

        listChatsAdapter.notifyDataSetChanged()
    }

    override fun onCancelled(databaseError: DatabaseError) {}

}
