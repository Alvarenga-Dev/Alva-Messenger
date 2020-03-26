package com.alvarengadev.alvamessenger.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.utils.RoutersUtils
import kotlinx.android.synthetic.main.activity_friends.*

class FriendsActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        initToolbar()
    }

    private fun initToolbar() {
        toolbarFriends.title = ""
        setSupportActionBar(toolbarFriends)
        toolbarFriends.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbarFriends.setNavigationOnClickListener(this)
    }

    private fun routerBackHome() {
        startActivity(RoutersUtils.router(applicationContext, HomeActivity::class.java))
        finish()
    }

    override fun onClick(view: View) {
        routerBackHome()
    }

    override fun onBackPressed() {
        routerBackHome()
    }
}
