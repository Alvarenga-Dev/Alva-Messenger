package com.alvarengadev.alvamessenger.view.adapters.friends

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.data.domain.Friend

class ViewHolderFriends(
    itemView: View,
    listFriends: ArrayList<Friend>,
    listener: FriendItemClick?
) : RecyclerView.ViewHolder(itemView) {

    val nameFriend = itemView.findViewById(R.id.tvNameFriend) as TextView

    init {
        itemView.setOnClickListener {
            if (listener != null) {
                val positionRcy = adapterPosition
                if (positionRcy != RecyclerView.NO_POSITION) listener.itemClick(listFriends[positionRcy])
            }
        }
    }

}