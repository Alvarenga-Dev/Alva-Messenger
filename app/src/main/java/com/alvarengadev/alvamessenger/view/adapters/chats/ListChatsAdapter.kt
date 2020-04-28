package com.alvarengadev.alvamessenger.view.adapters.chats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.data.domain.Chat
import com.alvarengadev.alvamessenger.view.adapters.interfaces.OnItemClickListener

class ListChatsAdapter(private val arrayChats: ArrayList<Chat>) :
    RecyclerView.Adapter<ViewHolderChat>() {

    private var listener: OnItemClickListener? = null

    fun setOnClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderChat {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_chat, parent, false)
        return ViewHolderChat(view, listener)
    }

    override fun getItemCount(): Int = arrayChats.size

    override fun onBindViewHolder(holder: ViewHolderChat, position: Int) {
        holder.lastMessage.text = arrayChats[position].message
        holder.nameFriend.text = arrayChats[position].name
    }
}