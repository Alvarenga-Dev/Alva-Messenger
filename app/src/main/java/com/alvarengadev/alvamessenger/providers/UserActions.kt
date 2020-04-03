package com.alvarengadev.alvamessenger.providers

import android.content.Context
import android.view.View
import com.alvarengadev.alvamessenger.models.User
import com.alvarengadev.alvamessenger.presenters.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.Base64Actions
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class UserActions(context: Context) {

    private val preferences = PreferencesUtils(context)

    fun validatorLogin(view: View, user: User) {

        val auth = SettingsFirebase.authReference

        auth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                preferences.saveUserDatas(Base64Actions.encodeBase64(user.email))
            }else {
                Snackbar.make(view, "Email ou Senha inválidos! :(", Snackbar.LENGTH_SHORT).show()
            }
        }

    }

    fun register(view: View, user: User) {

        val auth = SettingsFirebase.authReference

        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val settingsFirebase = SettingsFirebase.databaseReference
                    user.id = Base64Actions.encodeBase64( user.email )
                    settingsFirebase.child("Users").child(user.id).setValue(user)
                    preferences.saveUserDatas(user.id)
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

    companion object {
        fun signOut() {
            val auth = SettingsFirebase.authReference
            auth.signOut()
        }
    }

}
