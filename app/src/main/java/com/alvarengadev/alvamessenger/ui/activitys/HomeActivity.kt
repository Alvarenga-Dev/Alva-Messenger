package com.alvarengadev.alvamessenger.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.presenters.SettingsFirebase
import com.alvarengadev.alvamessenger.providers.UserActions
import com.alvarengadev.alvamessenger.utils.RoutersUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initDrawer()

        fabFriends.setOnClickListener(this)
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
        navigationHome.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawerLayoutHome.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutHome.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onClick(view: View) {
        startActivity(RoutersUtils.router(applicationContext, FriendsActivity::class.java))
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        //Ponto a ser refatorado, usar if / else ou when é triste :p (Programação Procedural -> POO)
        return when (menuItem.itemId) {
            R.id.menuProfile -> {
                startActivity(RoutersUtils.router(applicationContext, ProfileActivity::class.java))
                true
            }
            R.id.menuLogout -> {
                UserActions.signOut()
                startActivity(RoutersUtils.router(applicationContext, SignInActivity::class.java))
                finish()
                true
            }
            else -> {
                false
            }
        }
    }
}
