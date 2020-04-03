package com.alvarengadev.alvamessenger.view.introscreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.presenters.SettingsFirebase
import com.alvarengadev.alvamessenger.view.activitys.HomeActivity
import com.alvarengadev.alvamessenger.view.activitys.SignInActivity
import com.alvarengadev.alvamessenger.utils.RoutesUtils

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        checkLogin()
    }

    fun checkLogin() {
        val auth = SettingsFirebase.authReference

        val handler = Handler()

        val intent = if(auth.currentUser != null) {
            RoutesUtils.routes(applicationContext, HomeActivity::class.java)
        } else {
            RoutesUtils.routes(applicationContext, SignInActivity::class.java)
        }

        handler.postDelayed({
            startActivity(intent)
            finish()
        }, 1200)
    }
}
