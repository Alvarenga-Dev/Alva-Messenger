package com.alvarengadev.alvamessenger.presenter.friend.list

import com.alvarengadev.alvamessenger.data.domain.Friend
import com.alvarengadev.alvamessenger.data.firebase.SettingsFirebase
import com.alvarengadev.alvamessenger.view.adapters.friends.ListFriendsAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class ListFriendPresenter(private val view: ListFriendInterface.View) :
    ListFriendInterface.Presenter, ValueEventListener {

    private val databaseReference = SettingsFirebase.databaseReference
    private val arrayFriends = ArrayList<Friend>()
    private val listFriendsAdapter = ListFriendsAdapter(arrayFriends)

    override fun stopGetFriends() {
        databaseReference.removeEventListener(this)
    }

    override fun getAdapter(): ListFriendsAdapter {
        databaseReference
            .child("Friends")
            .child(view.userKey()!!)
            .addValueEventListener(this)

        return listFriendsAdapter
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {
        arrayFriends.clear()

        for (data in dataSnapshot.children) {
            val friend = data.getValue(Friend::class.java)!!
            arrayFriends.add(friend)
        }

        listFriendsAdapter.notifyDataSetChanged()
    }

    override fun onCancelled(databaseError: DatabaseError) {
    }

}
