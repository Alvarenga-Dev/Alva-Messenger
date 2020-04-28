package com.alvarengadev.alvamessenger.view.adapters.messages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.data.domain.Message
import com.alvarengadev.alvamessenger.utils.PreferencesUtils

class ListMessagesAdapter(private val messages: ArrayList<Message>) :
    RecyclerView.Adapter<ViewHolderMessages>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMessages {

        val preferences = PreferencesUtils(parent.context)
        val idUser = preferences.getUserKey()

        val view = if (idUser!! == messages[parent.childCount].id) viewMessage(
            parent,
            R.layout.list_message_user
        ) else viewMessage(parent, R.layout.list_message_friend)

        return ViewHolderMessages(view)
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: ViewHolderMessages, position: Int) {
        holder.tvMessage.text = messages[position].message
    }

    private fun viewMessage(parent: ViewGroup, layout: Int): View =
        LayoutInflater.from(parent.context).inflate(layout, parent, false)

}
