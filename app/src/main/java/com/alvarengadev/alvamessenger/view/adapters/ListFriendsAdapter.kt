package com.alvarengadev.alvamessenger.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.interfaces.OnItemClickListener

class ListFriendsAdapter(private val listFriends: ArrayList<String>): RecyclerView.Adapter<ListFriendsAdapter.ViewHolderFriends>() {

    private var listener: OnItemClickListener? = null


    fun setOnClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFriends {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_friends, parent, false)

        return ViewHolderFriends(view, listener)
    }

    override fun getItemCount(): Int {
        return listFriends.size
    }

    override fun onBindViewHolder(holder: ViewHolderFriends, position: Int) {

        holder.nameFriend.text = listFriends[position]

    }

    fun addFriendNotify(name: String) {
        listFriends.add(name)
        notifyItemInserted( itemCount )
    }


    class ViewHolderFriends(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {

        val nameFriend = itemView.findViewById(R.id.tvNameFriend) as TextView

        init {
            itemView.setOnClickListener {
                if (listener != null) {
                    val positionRcy = adapterPosition
                    if (positionRcy != RecyclerView.NO_POSITION) listener.onItemClick(positionRcy)
                }
            }
        }

    }

}