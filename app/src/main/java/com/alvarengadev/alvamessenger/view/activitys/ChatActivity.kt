package com.alvarengadev.alvamessenger.view.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.interfaces.Toolbar
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity(), Toolbar {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initToolbar()
    }

    override fun initToolbar() {
        setSupportActionBar(toolbarChat)
        toolbarChat.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbarChat.setNavigationOnClickListener{ finish() }
    }

}
