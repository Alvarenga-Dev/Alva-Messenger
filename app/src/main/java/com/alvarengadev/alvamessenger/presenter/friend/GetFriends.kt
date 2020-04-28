package com.alvarengadev.alvamessenger.presenter.friend

import android.content.Context
import android.content.Intent
import com.alvarengadev.alvamessenger.view.adapters.interfaces.OnItemClickListener
import com.alvarengadev.alvamessenger.data.domain.Friend
import com.alvarengadev.alvamessenger.data.firebase.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.Consts
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.alvarengadev.alvamessenger.view.activitys.ChatActivity
import com.alvarengadev.alvamessenger.view.adapters.ListFriendsAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class GetFriends(private val context: Context) : ValueEventListener,
    OnItemClickListener {

    private val databaseReference = SettingsFirebase.databaseReference
    private val arrayFriends = ArrayList<Friend>()
    private val listFriendsAdapter = ListFriendsAdapter(arrayFriends)

    fun get(): ListFriendsAdapter {
        val preferences = PreferencesUtils(context)

        databaseReference
            .child("Friends")
            .child(preferences.getUserKey()!!)
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
            arrayFriends.add(friend!!)
        }

        listFriendsAdapter.notifyDataSetChanged()

    }

    override fun onCancelled(databaseError: DatabaseError) {
    }

    override fun onItemClick(postion: Int) {
        val intent = Intent(context, ChatActivity::class.java)
        intent.putExtra(
            Consts.FRIEND_NAME,
            "${arrayFriends[postion].firstName} ${arrayFriends[postion].lastName}"
        )
        intent.putExtra(Consts.FRIEND_EMAIL, arrayFriends[postion].email)
        context.startActivity(intent)
    }
}
