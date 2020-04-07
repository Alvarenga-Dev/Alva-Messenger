package com.alvarengadev.alvamessenger.view.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.interfaces.Toolbar
import com.alvarengadev.alvamessenger.utils.Consts
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity(), Toolbar {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val bundle = intent.extras

        initToolbar(bundle?.getString(Consts.FRIEND_NAME, ""))
    }

    override fun initToolbar(title: String?) {
        toolbarChat.title = title
        setSupportActionBar(toolbarChat)
        toolbarChat.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbarChat.setNavigationOnClickListener{ finish() }
    }

}
