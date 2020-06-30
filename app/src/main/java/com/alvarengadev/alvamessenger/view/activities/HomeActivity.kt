package com.alvarengadev.alvamessenger.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.data.domain.Chat
import com.alvarengadev.alvamessenger.presenter.chat.list.ListChatInterface
import com.alvarengadev.alvamessenger.presenter.chat.list.ListChatPresenter
import com.alvarengadev.alvamessenger.presenter.user.logout.LogoutPresenter
import com.alvarengadev.alvamessenger.utils.Base64Actions
import com.alvarengadev.alvamessenger.utils.Constants
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.alvarengadev.alvamessenger.utils.RoutesUtils
import com.alvarengadev.alvamessenger.view.adapters.chat.ChatItemClick
import com.alvarengadev.alvamessenger.view.navigation.HeaderNavigationDrawer
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener,
    ListChatInterface.View, ChatItemClick {

    private lateinit var listChatsPresenter: ListChatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        listChatsPresenter = ListChatPresenter(this)
        initDrawer()
        initListChats()
        fabFriends.setOnClickListener(this)
    }

    private fun initListChats() {
        val listChatsAdapter = listChatsPresenter.getAdapter()
        rcyChats.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = listChatsAdapter
        }

        listChatsAdapter.setOnClickListener(this)
    }

    private fun initDrawer() {
        toolbarHome.title = ""
        setSupportActionBar(toolbarHome)
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayoutHome,
            toolbarHome,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayoutHome.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        val viewHeader = HeaderNavigationDrawer(this@HomeActivity)
        viewHeader.setView(navigationHome)

        navigationHome.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawerLayoutHome.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutHome.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onStop() {
        super.onStop()
        listChatsPresenter.stopGetChats()
    }

    override fun onClick(view: View) =
        startActivity(RoutesUtils.routes(this@HomeActivity, FriendsActivity::class.java))

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        //Ponto a ser refatorado (Programação Procedural -> POO)
        return when (menuItem.itemId) {
            R.id.menuProfile -> {
                startActivity(RoutesUtils.routes(applicationContext, ProfileActivity::class.java))
                true
            }
            R.id.menuLogout -> {
                LogoutPresenter.signOut()
                PreferencesUtils(this@HomeActivity).saveUserDatas("", "")
                listChatsPresenter.stopGetChats()
                startActivity(RoutesUtils.routes(applicationContext, SignInActivity::class.java))
                finish()
                true
            }
            else -> {
                false
            }
        }
    }

    override fun itemClick(chat: Chat) {
        val intent = Intent(this@HomeActivity, MessagesActivity::class.java)
        intent.putExtra(Constants.FRIEND_NAME, chat.name)
        intent.putExtra(Constants.FRIEND_EMAIL, Base64Actions.decodeBase64(chat.idUser))
        startActivity(intent)
    }

    override fun userKey(): String? = PreferencesUtils(this@HomeActivity).getUserKey()
}
