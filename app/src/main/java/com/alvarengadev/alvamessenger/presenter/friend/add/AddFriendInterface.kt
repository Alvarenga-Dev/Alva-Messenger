package com.alvarengadev.alvamessenger.presenter.friend.add

interface AddFriendInterface {

    interface View {
        fun getUserKey(): String?
        fun error(message: String)
        fun closeDialog(isAdd: Boolean)
    }

    interface Presenter {
        fun getIdFriend(): String
        fun addFriend()
    }
}
