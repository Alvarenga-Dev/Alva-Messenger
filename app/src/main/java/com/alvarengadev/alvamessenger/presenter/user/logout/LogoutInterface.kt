package com.alvarengadev.alvamessenger.presenter.user.logout

interface LogoutInterface {
    interface View {
        fun removeUser()
    }

    interface Presenter {
        fun signOut()
    }
}
