package com.alvarengadev.alvamessenger.presenter.user.register

import com.alvarengadev.alvamessenger.data.domain.User

interface RegisterUserInterface {

    interface View {
        fun saveUser(user: User)
        fun registerSuccess(register: Boolean)
        fun error(message: String)
    }

    interface Presenter {
        fun register(user: User)
    }
}