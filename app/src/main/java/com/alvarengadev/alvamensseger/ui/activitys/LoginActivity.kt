package com.alvarengadev.alvamensseger.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alvarengadev.alvamensseger.R
import com.alvarengadev.alvamensseger.utils.RoutersUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        startActivity(RoutersUtils.router(applicationContext, HomeActivity::class.java))
    }
}
