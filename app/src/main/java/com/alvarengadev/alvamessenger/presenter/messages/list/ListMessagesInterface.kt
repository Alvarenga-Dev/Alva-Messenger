package com.alvarengadev.alvamessenger.presenter.messages.list

import com.alvarengadev.alvamessenger.view.adapters.messages.ListMessagesAdapter

interface ListMessagesInterface {

    interface View {
        fun userKey(): String?
        fun friendKey(): String?
        fun error(message: String)
    }

    interface Presenter {
        fun stopGetMessages()
        fun getAdapter(): ListMessagesAdapter
    }
}