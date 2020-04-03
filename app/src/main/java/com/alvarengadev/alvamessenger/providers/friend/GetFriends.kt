package com.alvarengadev.alvamessenger.providers.friend

import android.content.Context
import com.alvarengadev.alvamessenger.interfaces.OnItemClickListener
import com.alvarengadev.alvamessenger.models.Friend
import com.alvarengadev.alvamessenger.presenters.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.alvarengadev.alvamessenger.utils.RoutesUtils
import com.alvarengadev.alvamessenger.view.activitys.ChatActivity
import com.alvarengadev.alvamessenger.view.adapters.ListFriendsAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class GetFriends(private val context: Context): ValueEventListener, OnItemClickListener {

    private val databaseReference = SettingsFirebase.databaseReference
    private val arrayFriends = ArrayList<String>()
    private val listFriendsAdapter = ListFriendsAdapter(arrayFriends)

    fun get(): ListFriendsAdapter {
        val preferences = PreferencesUtils(context)

        databaseReference
            .child("Friends")
            .child(preferences.getUserDatas()!!)
            .addValueEventListener(this)

        listFriendsAdapter.setOnClickListener(this)

        return listFriendsAdapter
    }

    fun getStop() {
        databaseReference.removeEventListener(this)
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {

        arrayFriends.clear()

        for (datas in dataSnapshot.children) {
            val friend = datas.getValue( Friend::class.java )
            arrayFriends.add("${friend!!.firstName} ${friend.lastName}")
        }

        listFriendsAdapter.notifyDataSetChanged()

    }

    override fun onCancelled(databaseError: DatabaseError) {
    }

    override fun onItemClick(postion: Int) {
        context.startActivity(RoutesUtils.routes(context, ChatActivity::class.java))
    }
}
