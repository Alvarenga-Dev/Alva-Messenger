package com.alvarengadev.alvamessenger.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.models.User
import com.alvarengadev.alvamessenger.providers.UserActions
import com.alvarengadev.alvamessenger.utils.InputsValidatorUtils
import com.alvarengadev.alvamessenger.utils.RoutesUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        buttonSignUpConfirm.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (validator(view)){
            startActivity(RoutesUtils.routes(applicationContext, HomeActivity::class.java))
            finish()
        }else{
            Snackbar.make(view, "Preencha os campos corretamente!", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun validator(view: View):Boolean {

        val firstName = inputSignUpFirstName.editText?.text.toString()
        val lastName = inputSignUpLastName.editText?.text.toString()
        val email = inputSignUpEmail.editText?.text.toString()
        val password = inputSignUpPassword.editText?.text.toString()

        val inputsValidatorUtils = InputsValidatorUtils(applicationContext)
        val isValidatorFirstName = inputsValidatorUtils.validateFirstName(inputSignUpFirstName, firstName)
        val isValidatorLastName = inputsValidatorUtils.validateLastName(inputSignUpLastName, lastName)
        val isValidatorEmail = inputsValidatorUtils.validateEmail(inputSignUpEmail, email)
        val isValidatorPassword = inputsValidatorUtils.validatePassword(inputSignUpPassword, password)

        return if (!isValidatorFirstName || !isValidatorLastName || !isValidatorEmail || !isValidatorPassword){
            false
        }else {
            val user = User(firstName, lastName, email, password)
            UserActions.register(view, user)
            true
        }

    }

}
