package com.alvarengadev.alvamessenger.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.data.domain.User
import com.alvarengadev.alvamessenger.presenter.user.register.RegisterUserInterface
import com.alvarengadev.alvamessenger.presenter.user.register.RegisterUserPresenter
import com.alvarengadev.alvamessenger.utils.InputsValidatorUtils
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.alvarengadev.alvamessenger.utils.RoutesUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), RegisterUserInterface.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        buttonSignUpConfirm.setOnClickListener { register() }
    }

    private fun register() {

        val name = inputSignUpName.editText?.text.toString()
        val email = inputSignUpEmail.editText?.text.toString()
        val password = inputSignUpPassword.editText?.text.toString()

        val inputsValidatorUtils = InputsValidatorUtils(this@SignUpActivity)
        val isValidatorName = inputsValidatorUtils.validateName(inputSignUpName, name)
        val isValidatorEmail = inputsValidatorUtils.validateEmail(inputSignUpEmail, email)
        val isValidatorPassword =
            inputsValidatorUtils.validatePassword(inputSignUpPassword, password)

        if (isValidatorName && isValidatorEmail && isValidatorPassword) {
            val user = User(name, email, password)
            RegisterUserPresenter(this).register(user)
        }
    }

    override fun saveUser(id: String, name: String) =
        PreferencesUtils(this@SignUpActivity).saveUserDatas(id, name)

    override fun registerSuccess(register: Boolean) {
        if (register) {
            startActivity(RoutesUtils.routes(applicationContext, HomeActivity::class.java))
            finish()
        }
    }

    override fun error(message: String) =
        Snackbar.make(containerSignUp, message, Snackbar.LENGTH_SHORT).show()
}