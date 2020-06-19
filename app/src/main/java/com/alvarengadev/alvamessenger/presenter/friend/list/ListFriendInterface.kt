package com.alvarengadev.alvamessenger.presenter.friend.list

import com.alvarengadev.alvamessenger.view.adapters.friends.ListFriendsAdapter

interface ListFriendInterface {
    interface View {
        fun userKey(): String?
    }

    interface Presenter {
        fun stopGetFriends()
        fun getAdapter(): ListFriendsAdapter
    }
}