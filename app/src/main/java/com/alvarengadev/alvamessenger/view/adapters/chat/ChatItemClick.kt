package com.alvarengadev.alvamessenger.view.adapters.chat

import com.alvarengadev.alvamessenger.data.domain.Chat

interface ChatItemClick {
    fun itemClick(chat: Chat)
}
