package com.alvarengadev.alvamensseger.providers

import android.content.Context
import android.widget.Toast
import com.alvarengadev.alvamensseger.models.User
import com.alvarengadev.alvamensseger.presenters.SettingsFirebase
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

abstract class RegisterUser {

    companion object {

        fun register(context: Context, user: User) {

            val auth = SettingsFirebase.authReference()

            auth?.createUserWithEmailAndPassword(user.email, user.password)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = task.result?.user
                        user.id = firebaseUser!!.uid
                        val settingsFirebase = SettingsFirebase.databaseReference()
                        settingsFirebase?.child("Users")?.child(user.id)?.setValue(user)
                    } else {

                        val error = try {
                            throw task.exception!!
                        }catch (networkException: FirebaseNetworkException){
                            "Sem conexão :("
                        }catch (authWeakPasswordException: FirebaseAuthWeakPasswordException){
                            "Sua senha não é forte!"
                        }

                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                }

        }

    }

}