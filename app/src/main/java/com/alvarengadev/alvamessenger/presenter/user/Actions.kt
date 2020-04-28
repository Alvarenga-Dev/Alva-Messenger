package com.alvarengadev.alvamessenger.presenter.user

import android.content.Context
import android.view.View
import com.alvarengadev.alvamessenger.data.domain.User
import com.alvarengadev.alvamessenger.data.firebase.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.Base64Actions
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class Actions(context: Context, private val user: User) : ValueEventListener {

    private val preferences = PreferencesUtils(context)
    private val userId = Base64Actions.encodeBase64(user.email)

    fun login(view: View) {

        val auth = SettingsFirebase.authReference
        val database = SettingsFirebase.databaseReference
            .child("Users")
            .child(userId)

        database.addListenerForSingleValueEvent(this)

        auth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Snackbar.make(view, "Email ou Senha inválidos! :(", Snackbar.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {

        val userData = dataSnapshot.getValue(User::class.java)
        preferences.saveUserDatas(userId, "${userData!!.firstName} ${userData.lastName}")

    }

    override fun onCancelled(databaseError: DatabaseError) {}


    companion object {
        fun signOut() = SettingsFirebase.authReference.signOut()
    }
}
