package com.alvarengadev.alvamessenger.presenter.user.logout

import com.alvarengadev.alvamessenger.data.firebase.database.user.FirebaseUserLogout

class LogoutPresenter(private val view: LogoutInterface.View) : LogoutInterface.Presenter {
    override fun signOut() {
        FirebaseUserLogout.logout()
        view.removeUser()
    }
}
