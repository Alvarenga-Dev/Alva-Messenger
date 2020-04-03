package com.alvarengadev.alvamessenger.view.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.interfaces.Toolbar
import com.alvarengadev.alvamessenger.providers.friend.GetFriends
import com.alvarengadev.alvamessenger.view.dialogs.AddFriendsDialog
import kotlinx.android.synthetic.main.activity_friends.*

class FriendsActivity : AppCompatActivity(), Toolbar {

    private val getFriends = GetFriends(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        initToolbar()
        initListFriends()

        fabOpenDialog.setOnClickListener { openDialog() }

    }

    private fun initListFriends() {

        contactRcy.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            adapter = getFriends.get()
        }
    }

    private fun openDialog() = AddFriendsDialog().show(supportFragmentManager, "Add Friends Dialog")

    override fun initToolbar() {
        toolbarFriends.title = ""
        setSupportActionBar(toolbarFriends)
        toolbarFriends.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbarFriends.setNavigationOnClickListener{ finish() }
    }

    override fun onBackPressed() = finish()

    override fun onStop() {
        super.onStop()
        getFriends.getStop()
    }
}
