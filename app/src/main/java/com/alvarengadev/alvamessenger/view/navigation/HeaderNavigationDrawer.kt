package com.alvarengadev.alvamessenger.view.navigation

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.google.android.material.navigation.NavigationView

class HeaderNavigationDrawer(private val context: Context) {
    fun setView(navigation: NavigationView) {
        val preferences = PreferencesUtils(context)
        val view = navigation.inflateHeaderView(R.layout.header_drawer)

        val ivUserProfile = view.findViewById(R.id.iv_user_profile) as ImageView
        val tvUserName = view.findViewById(R.id.tv_user_name) as TextView

        tvUserName.text = preferences.getUserName()
    }
}
