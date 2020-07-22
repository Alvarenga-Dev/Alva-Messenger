package com.alvarengadev.alvamessenger.presenter.friend.add

import com.alvarengadev.alvamessenger.data.domain.Friend
import com.alvarengadev.alvamessenger.data.domain.User
import com.alvarengadev.alvamessenger.data.firebase.settings.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.Base64Actions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AddFriendPresenter(
    private val view: AddFriendInterface.View,
    private val friendEmail: String
) : ValueEventListener, AddFriendInterface.Presenter {

    override fun getIdFriend(): String = Base64Actions.encodeBase64(friendEmail)

    override fun addFriend() {
        val databaseReference = SettingsFirebase.databaseReference
        databaseReference.child("Users").child(getIdFriend())
            .addValueEventListener(this)
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {
        if (dataSnapshot.exists()) {
            val friendData = dataSnapshot.getValue(User::class.java)!!
            val databaseReference = SettingsFirebase.databaseReference
                .child("Friends")
                .child(view.getUserKey()!!)
                .child(getIdFriend())

            val friend = Friend(getIdFriend(), friendData.name, friendData.email)
            databaseReference.setValue(friend)
            view.closeDialog(true)
        } else {
            view.closeDialog(false)
            view.error("Usuário não possui cadastro :(")
        }
    }

    override fun onCancelled(databaseError: DatabaseError) =
        view.error("Falha ao adicionar o seu amigo :(")
}
