package com.alvarengadev.alvamensseger.ui.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.Toast
import com.alvarengadev.alvamensseger.R
import com.alvarengadev.alvamensseger.models.User
import com.alvarengadev.alvamensseger.presenters.SettingsFirebase
import com.alvarengadev.alvamensseger.providers.RegisterUser
import com.alvarengadev.alvamensseger.utils.InputsValidatorUtils
import com.alvarengadev.alvamensseger.utils.RoutersUtils
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        buttonSignUpConfirm.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (validator()){
            startActivity(RoutersUtils.router(applicationContext, HomeActivity::class.java))
        }
    }

    private fun validator():Boolean {

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
            val auth = SettingsFirebase.authReference()
            val user = User(firstName, lastName, email, password)
            RegisterUser.register(applicationContext, user)
            true
        }

    }

}
