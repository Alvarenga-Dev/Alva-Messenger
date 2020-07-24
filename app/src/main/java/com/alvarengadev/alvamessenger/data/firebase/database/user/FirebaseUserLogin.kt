package com.alvarengadev.alvamessenger.data.firebase.database.user

import com.alvarengadev.alvamessenger.data.domain.User
import com.alvarengadev.alvamessenger.data.firebase.settings.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.Constants
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.ValueEventListener

class FirebaseUserLogin(private val valueEventListener: ValueEventListener) {
    private val auth = SettingsFirebase.authReference

    fun login(user: User): Task<AuthResult> =
        auth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener {
            if (it.isSuccessful) {
                SettingsFirebase.databaseReference
                    .child(Constants.FIREBASE_CHILD_USERS)
                    .child(user.id)
                    .addListenerForSingleValueEvent(valueEventListener)
            }
        }
}
