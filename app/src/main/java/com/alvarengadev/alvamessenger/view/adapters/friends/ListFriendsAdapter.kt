package com.alvarengadev.alvamessenger.view.adapters.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.data.domain.Friend

class ListFriendsAdapter(private val listFriends: ArrayList<Friend>) :
    RecyclerView.Adapter<ViewHolderFriends>() {

    private var listener: FriendItemClick? = null

    fun setOnClickListener(listener: FriendItemClick?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFriends {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_friends, parent, false)

        return ViewHolderFriends(view, listFriends, listener)
    }

    override fun getItemCount(): Int = listFriends.size

    override fun onBindViewHolder(holder: ViewHolderFriends, position: Int) {
        holder.nameFriend.text = listFriends[position].name
    }
}
