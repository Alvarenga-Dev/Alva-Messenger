package com.alvarengadev.alvamessenger.view.adapters.chat

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.data.domain.Chat

class ViewHolderChat(itemView: View, arrayChats: ArrayList<Chat>, listener: ChatItemClick?) :
    RecyclerView.ViewHolder(itemView) {

    val nameFriend = itemView.findViewById(R.id.tvNameFriend) as TextView
    val lastMessage = itemView.findViewById(R.id.tvLastMessage) as TextView

    init {
        itemView.setOnClickListener {
            if (listener != null) {
                val positionRcy = adapterPosition
                if (positionRcy != RecyclerView.NO_POSITION) listener.itemClick(arrayChats[positionRcy])
            }
        }
    }
}
