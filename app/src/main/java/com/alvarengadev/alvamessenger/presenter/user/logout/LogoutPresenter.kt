package com.alvarengadev.alvamessenger.presenter.user.logout

import com.alvarengadev.alvamessenger.data.firebase.SettingsFirebase

class LogoutPresenter {
    companion object {
        fun signOut() = SettingsFirebase.authReference.signOut()
    }
}
