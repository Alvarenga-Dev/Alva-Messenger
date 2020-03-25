package com.alvarengadev.alvamessenger.ui.introscreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.presenters.SettingsFirebase
import com.alvarengadev.alvamessenger.ui.activitys.HomeActivity
import com.alvarengadev.alvamessenger.ui.activitys.SignInActivity
import com.alvarengadev.alvamessenger.utils.RoutersUtils

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val auth = SettingsFirebase.authReference

        val handler = Handler()

        val intent = if(auth.currentUser != null) {
            RoutersUtils.router(applicationContext, HomeActivity::class.java)
        } else {
            RoutersUtils.router(applicationContext, SignInActivity::class.java)
        }

        handler.postDelayed({
            startActivity(intent)
            finish()
        }, 1200)

    }
}
