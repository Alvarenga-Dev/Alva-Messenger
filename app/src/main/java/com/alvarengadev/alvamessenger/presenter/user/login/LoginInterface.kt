package com.alvarengadev.alvamessenger.presenter.user.login

interface LoginInterface {

    interface View {
        fun error(message: String)
        fun saveUser(name: String, email: String)
        fun loginSuccess(isLogin: Boolean)
    }

    interface Presenter {
        fun login()
    }
}
