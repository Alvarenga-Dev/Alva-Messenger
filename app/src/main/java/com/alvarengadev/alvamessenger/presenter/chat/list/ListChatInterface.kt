package com.alvarengadev.alvamessenger.presenter.chat.list

import com.alvarengadev.alvamessenger.view.adapters.chat.ListChatsAdapter

interface ListChatInterface {

    interface View {
        fun userKey(): String?
    }

    interface Presenter {
        fun getAdapter(): ListChatsAdapter
        fun stopGetChats()
    }
}
