package com.alvarengadev.alvamessenger.view.adapters.friends

import com.alvarengadev.alvamessenger.data.domain.Friend

interface FriendItemClick {
    fun itemClick(friend: Friend)
}