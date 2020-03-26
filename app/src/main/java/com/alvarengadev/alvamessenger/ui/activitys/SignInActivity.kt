package com.alvarengadev.alvamessenger.ui.activitys

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.utils.RoutesUtils
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        buttonSignInWithMail.setOnClickListener { startRouter(LoginActivity::class.java) }

        buttonCreateAccount.apply {
            text = stringCustomSignUp()
            setOnClickListener { startRouter(SignUpActivity::class.java) }
        }

    }

    private fun startRouter(destiny: Class<*>){
        startActivity(RoutesUtils.routes(applicationContext, destiny))
    }

    private fun stringCustomSignUp():SpannableString {

        val textSignUp = SpannableString( resources.getString(R.string.button_create_user) )

        textSignUp.setSpan(
            StyleSpan(Typeface.BOLD),
            20,
            38,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )

        return textSignUp
    }
}
