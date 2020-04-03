package com.alvarengadev.alvamessenger.view.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.models.User
import com.alvarengadev.alvamessenger.providers.UserActions
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
        if (validator(view)){
            startActivity(RoutesUtils.routes(applicationContext, HomeActivity::class.java))
            finish()
        }
    }

    private fun validator(view: View): Boolean {
        val email = inputLoginEmail.editText?.text.toString();
        val password = inputLoginPasword.editText?.text.toString();

        val inputsValidatorUtils = InputsValidatorUtils(applicationContext)
        val isValidatorEmail = inputsValidatorUtils.validateEmail(inputLoginEmail, email)
        val isValidatorPassword = inputsValidatorUtils.validatePassword(inputLoginPasword, password)

        return if( !isValidatorEmail && !isValidatorPassword ) {
            Snackbar.make(view, "Preencha os campos corretamente!", Snackbar.LENGTH_SHORT).show()
            false
        } else {
            val user = User()
            user.email = email
            user.password = password
            val userActions = UserActions(applicationContext)
            userActions.validatorLogin(view, user)
            true
        }
    }
}
