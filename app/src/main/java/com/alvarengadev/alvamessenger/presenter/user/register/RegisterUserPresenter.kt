package com.alvarengadev.alvamessenger.presenter.user.register

import com.alvarengadev.alvamessenger.data.domain.User
import com.alvarengadev.alvamessenger.data.firebase.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.Base64Actions
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class RegisterUserPresenter(private val view: RegisterUserInterface.View) :
    RegisterUserInterface.Presenter {

    override fun register(user: User) {
        val auth = SettingsFirebase.authReference
        val userId = Base64Actions.encodeBase64(user.email)

        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val settingsFirebase = SettingsFirebase.databaseReference
                    settingsFirebase.child("Users").child(userId).setValue(user)
                    view.saveUser(userId, user.name)
                    view.registerSuccess(true)
                } else {
                    val messageError = try {
                        throw task.exception!!
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

}
