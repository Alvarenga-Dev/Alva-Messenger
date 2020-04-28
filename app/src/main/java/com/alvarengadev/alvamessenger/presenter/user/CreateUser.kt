package com.alvarengadev.alvamessenger.presenter.user

import android.view.View
import com.alvarengadev.alvamessenger.data.domain.User
import com.alvarengadev.alvamessenger.data.firebase.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.Base64Actions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class CreateUser {

    fun register(view: View, user: User) {

        val auth = SettingsFirebase.authReference
        val userId = Base64Actions.encodeBase64(user.email)

        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val settingsFirebase = SettingsFirebase.databaseReference

                    settingsFirebase.child("Users").child(userId).setValue(user)

                } else {
                    val error = try {
                        throw task.exception!!
                    } catch (networkException: FirebaseNetworkException) {
                        "Sem conexão :("
                    } catch (authWeakPasswordException: FirebaseAuthWeakPasswordException) {
                        "Sua senha não é forte!"
                    }
                    Snackbar.make(view, error, Snackbar.LENGTH_SHORT).show()
                }
            }
    }

}
