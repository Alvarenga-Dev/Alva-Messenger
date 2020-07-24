package com.alvarengadev.alvamessenger.presenter.user.register

import com.alvarengadev.alvamessenger.data.domain.User
import com.alvarengadev.alvamessenger.data.firebase.database.user.FirebaseUserRegister
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class RegisterUserPresenter(private val view: RegisterUserInterface.View) :
    RegisterUserInterface.Presenter {

    override fun register(user: User) {

        FirebaseUserRegister.register(user)
            .addOnSuccessListener {
                view.saveUser(user)
                view.registerSuccess(true)
            }.addOnFailureListener {
                val messageError = try {
                    throw it
                } catch (networkException: FirebaseNetworkException) {
                    "Sem conexão :("
                } catch (authWeakPasswordException: FirebaseAuthWeakPasswordException) {
                    "Sua senha não é forte!"
                }
                view.registerSuccess(false)
                view.error(messageError)
            }
    }
}
