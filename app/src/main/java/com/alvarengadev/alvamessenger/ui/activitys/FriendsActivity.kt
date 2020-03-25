package com.alvarengadev.alvamessenger.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alvarengadev.alvamessenger.R
import kotlinx.android.synthetic.main.activity_friends.*

class FriendsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        initToolbar();
    }

    fun initToolbar() {
        toolbarFriends.title = "";
        setSupportActionBar(toolbarFriends)
    }
}
