package com.alvarengadev.alvamessenger.presenter.user.login

import com.alvarengadev.alvamessenger.data.domain.User
import com.alvarengadev.alvamessenger.data.firebase.database.user.FirebaseUserLogin
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class LoginPresenter(private val view: LoginInterface.View) :
    ValueEventListener,
    LoginInterface.Presenter {

    override fun login(user: User) {
        val firebaseUserLogin = FirebaseUserLogin(this)

        firebaseUserLogin.login(user)
            .addOnFailureListener {
                view.loginSuccess(false)
                view.error("Login ou Senha inválidos!")
            }
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {
        val userData = dataSnapshot.getValue(User::class.java)!!
        view.saveUser(userData)
        view.loginSuccess(true)
    }

    override fun onCancelled(databaseError: DatabaseError) =
        view.error("Algo deu errado com o seu login :(")
}
