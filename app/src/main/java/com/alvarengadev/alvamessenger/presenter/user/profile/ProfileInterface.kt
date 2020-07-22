package com.alvarengadev.alvamessenger.presenter.user.profile

interface ProfileInterface {

    interface View {
        fun toastMessage(message: String)
    }

    interface Presenter {
        fun saveImageProfile(id: String, byteDataImageProfile: ByteArray)
    }
}