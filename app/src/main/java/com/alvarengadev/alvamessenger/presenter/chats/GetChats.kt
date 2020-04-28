package com.alvarengadev.alvamessenger.presenter.chats

import android.content.Context
import android.content.Intent
import com.alvarengadev.alvamessenger.data.domain.Chat
import com.alvarengadev.alvamessenger.data.firebase.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.Base64Actions
import com.alvarengadev.alvamessenger.utils.Consts
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.alvarengadev.alvamessenger.view.activitys.ChatActivity
import com.alvarengadev.alvamessenger.view.adapters.chats.ListChatsAdapter
import com.alvarengadev.alvamessenger.view.adapters.interfaces.OnItemClickListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class GetChats(private val context: Context) : ValueEventListener, OnItemClickListener {

    private val database = SettingsFirebase.databaseReference
    private val arrayChats = ArrayList<Chat>()
    private val listChatsAdapter = ListChatsAdapter(arrayChats)

    fun get(): ListChatsAdapter {
        val preferences = PreferencesUtils(context)

        database.child("Chats")
            .child(preferences.getUserKey()!!)
            .addValueEventListener(this)

        listChatsAdapter.setOnClickListener(this)

        return listChatsAdapter
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {

        arrayChats.clear()

        for (data in dataSnapshot.children) {
            val chat = data.getValue(Chat::class.java)
            arrayChats.add(chat!!)
        }

        listChatsAdapter.notifyDataSetChanged()

    }

    override fun onCancelled(databaseError: DatabaseError) {}

    override fun onItemClick(postion: Int) {
        val intent = Intent(context, ChatActivity::class.java)
        intent.putExtra(Consts.FRIEND_NAME, arrayChats[postion].name)
        intent.putExtra(Consts.FRIEND_EMAIL, Base64Actions.decodeBase64(arrayChats[postion].idUser))
        context.startActivity(intent)
    }

}
