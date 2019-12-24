package com.alvarengadev.alvamensseger.ui.introscreens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.alvarengadev.alvamensseger.R
import com.alvarengadev.alvamensseger.ui.activitys.SignInActivity
import com.alvarengadev.alvamensseger.utils.RoutersUtils

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val handler = Handler()

        val intent = RoutersUtils.router(applicationContext, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        handler.postDelayed(
            { startActivity(intent) },
            1200)


    }
}
