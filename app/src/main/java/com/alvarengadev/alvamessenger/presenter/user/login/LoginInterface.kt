package com.alvarengadev.alvamessenger.presenter.user.login

import com.alvarengadev.alvamessenger.data.domain.User

interface LoginInterface {

    interface View {
        fun error(message: String)
        fun saveUser(user: User)
        fun loginSuccess(isLogin: Boolean)
    }

    interface Presenter {
        fun login(user: User)
    }
}
