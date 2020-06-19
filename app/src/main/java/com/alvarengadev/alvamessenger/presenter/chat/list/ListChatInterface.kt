package com.alvarengadev.alvamessenger.presenter.chat.list

import com.alvarengadev.alvamessenger.view.adapters.chats.ListChatsAdapter

interface ListChatInterface {

    interface View {
        //fun userKey(): String?
    }

    interface Presenter {
        fun getAdapter(id: String?): ListChatsAdapter
        fun stopGetChats()
    }
}
