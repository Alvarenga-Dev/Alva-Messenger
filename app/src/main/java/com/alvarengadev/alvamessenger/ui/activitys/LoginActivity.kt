package com.alvarengadev.alvamessenger.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.models.User
import com.alvarengadev.alvamessenger.presenters.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.InputsValidatorUtils
import com.alvarengadev.alvamessenger.utils.RoutesUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener(this)

    }


    override fun onClick(view: View) {

        val email = inputLoginEmail.editText?.text.toString();
        val password = inputLoginPasword.editText?.text.toString();

        val inputsValidatorUtils = InputsValidatorUtils(applicationContext)
        val isValidatorEmail = inputsValidatorUtils.validateEmail(inputLoginEmail, email)
        val isValidatorPassword = inputsValidatorUtils.validatePassword(inputLoginPasword, password)

        if( isValidatorEmail && isValidatorPassword ) {
            val user = User();
            user.email = email
            user.password = password
            validatorLogin(view, user.email, user.password)
        }

    }

    private fun validatorLogin(view: View, email: String, password: String) {

        val auth = SettingsFirebase.authReference
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                startActivity(RoutesUtils.routes(applicationContext, HomeActivity::class.java))
                finish()
            } else {
                Snackbar.make(view, "Email ou Senha inválidos! :(", Snackbar.LENGTH_SHORT).show()
            }
        }

    }
}
