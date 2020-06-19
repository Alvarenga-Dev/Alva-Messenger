package com.alvarengadev.alvamessenger.view.adapters.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.data.domain.Chat

class ListChatsAdapter(private val arrayChats: ArrayList<Chat>) :
    RecyclerView.Adapter<ViewHolderChat>() {

    private var listener: ChatItemClick? = null

    fun setOnClickListener(listener: ChatItemClick?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderChat {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_chat, parent, false)
        return ViewHolderChat(view, arrayChats, listener)
    }

    override fun getItemCount(): Int = arrayChats.size

    override fun onBindViewHolder(holder: ViewHolderChat, position: Int) {
        holder.lastMessage.text = arrayChats[position].message
        holder.nameFriend.text = arrayChats[position].name
    }
}