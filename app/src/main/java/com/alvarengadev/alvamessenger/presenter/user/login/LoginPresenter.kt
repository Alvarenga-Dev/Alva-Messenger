package com.alvarengadev.alvamessenger.presenter.user.login

import com.alvarengadev.alvamessenger.data.domain.User
import com.alvarengadev.alvamessenger.data.firebase.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.Base64Actions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class LoginPresenter(private val view: LoginInterface.View, private val user: User) :
    ValueEventListener,
    LoginInterface.Presenter {

    override fun login() {
        val auth = SettingsFirebase.authReference

        auth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                SettingsFirebase.databaseReference
                    .child("Users")
                    .child(Base64Actions.encodeBase64(user.email))
                    .addListenerForSingleValueEvent(this@LoginPresenter)
            } else {
                view.loginSuccess(false)
                view.error("Login ou Senha inválidos!")
            }
        }
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {
        val userData = dataSnapshot.getValue(User::class.java)!!
        view.saveUser(userData.name, userData.email)
        view.loginSuccess(true)
    }

    override fun onCancelled(databaseError: DatabaseError) =
        view.error("Algo deu errado com o seu login :(")
}
