package com.alvarengadev.alvamessenger.providers

import android.view.View
import android.widget.Toast
import com.alvarengadev.alvamessenger.models.User
import com.alvarengadev.alvamessenger.presenters.SettingsFirebase
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

abstract class UserActions {

    companion object {

        fun register(view: View, user: User) {

            val auth = SettingsFirebase.authReference

            auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val settingsFirebase = SettingsFirebase.databaseReference
                        val firebaseUser = task.result?.user
                        user.id = firebaseUser!!.uid
                        settingsFirebase.child("Users").child(user.id).setValue(user)
                    } else {
                        val error = try {
                            throw task.exception!!
                        } catch(networkException: FirebaseNetworkException) {
                            "Sem conexão :("
                        } catch(authWeakPasswordException: FirebaseAuthWeakPasswordException) {
                            "Sua senha não é forte!"
                        }

                        Snackbar.make(view, error, Snackbar.LENGTH_SHORT).show()
                    }
                }
        }

        fun signOut() {
            val auth = SettingsFirebase.authReference
            auth.signOut()
        }

    }

}