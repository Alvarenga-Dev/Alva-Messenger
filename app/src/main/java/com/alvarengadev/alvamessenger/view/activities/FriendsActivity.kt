package com.alvarengadev.alvamessenger.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.data.domain.Friend
import com.alvarengadev.alvamessenger.presenter.friend.list.ListFriendInterface
import com.alvarengadev.alvamessenger.presenter.friend.list.ListFriendPresenter
import com.alvarengadev.alvamessenger.utils.Constants
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.alvarengadev.alvamessenger.view.adapters.friends.FriendItemClick
import com.alvarengadev.alvamessenger.view.dialogs.AddFriendsDialog
import kotlinx.android.synthetic.main.activity_friends.*

class FriendsActivity : AppCompatActivity(), ListFriendInterface.View, FriendItemClick {

    private lateinit var listFriendPresenter: ListFriendPresenter
    private val context = this@FriendsActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        listFriendPresenter = ListFriendPresenter(this)
        initToolbar()
        initListFriends()
        fabOpenDialog.setOnClickListener { openDialog() }
    }

    private fun initListFriends() {
        val friendAdapter = listFriendPresenter.getAdapter()
        contactRcy.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = friendAdapter
        }
        friendAdapter.setOnClickListener(context)
    }

    private fun openDialog() = AddFriendsDialog().show(supportFragmentManager, "Add Friends Dialog")

    private fun initToolbar() {
        toolbarFriends.title = ""
        setSupportActionBar(toolbarFriends)
        toolbarFriends.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbarFriends.setNavigationOnClickListener { finish() }
    }

    override fun onBackPressed() = finish()

    override fun onStop() {
        super.onStop()
        listFriendPresenter.stopGetFriends()
    }

    override fun userKey(): String? = PreferencesUtils(context).getUserKey()

    override fun itemClick(friend: Friend) {
        val intent = Intent(context, MessagesActivity::class.java)
        intent.putExtra(Constants.FRIEND_NAME, friend.name)
        intent.putExtra(Constants.FRIEND_EMAIL, friend.email)
        startActivity(intent)
    }
}
