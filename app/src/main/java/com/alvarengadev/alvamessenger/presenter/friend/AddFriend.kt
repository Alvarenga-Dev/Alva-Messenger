package com.alvarengadev.alvamessenger.presenter.friend

import android.content.Context
import android.widget.Toast
import com.alvarengadev.alvamessenger.data.domain.Friend
import com.alvarengadev.alvamessenger.data.domain.User
import com.alvarengadev.alvamessenger.data.firebase.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.Base64Actions
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AddFriend(private val context: Context, emailFriend: String): ValueEventListener{

    private val preferences = PreferencesUtils(context)
    private val idFriend = Base64Actions.encodeBase64(emailFriend)

    fun add() {
        val databaseReference = SettingsFirebase.databaseReference
        databaseReference.child("Users").child(idFriend)
            .addValueEventListener(this)
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {
       if (dataSnapshot.exists()) {
           val friendDatas = dataSnapshot.getValue(User::class.java)
           val databaseReference = SettingsFirebase.databaseReference
               .child("Friends")
               .child(preferences.getUserKey()!!)
               .child(idFriend)
           val friend = Friend(
               idFriend,
               friendDatas!!.firstName,
               friendDatas.lastName,
               friendDatas.email
           )

           databaseReference.setValue(friend)
       } else {
           Toast.makeText(context, "Usuário não possui cadastro.", Toast.LENGTH_SHORT).show()
       }
    }

    override fun onCancelled(databaseError: DatabaseError) {

    }

}

