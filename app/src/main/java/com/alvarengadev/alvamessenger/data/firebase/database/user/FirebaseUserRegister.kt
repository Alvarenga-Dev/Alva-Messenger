package com.alvarengadev.alvamessenger.data.firebase.database.user

import com.alvarengadev.alvamessenger.data.domain.User
import com.alvarengadev.alvamessenger.data.firebase.settings.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.Constants
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class FirebaseUserRegister {
    companion object {
        fun register(user: User): Task<AuthResult> {
            val auth = SettingsFirebase.authReference
            return auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val databaseReference = SettingsFirebase.databaseReference
                        databaseReference
                            .child(Constants.FIREBASE_CHILD_USERS)
                            .child(user.id)
                            .setValue(user)
                    }
                }
        }
    }
}
