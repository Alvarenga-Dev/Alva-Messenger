package com.alvarengadev.alvamessenger.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.data.domain.User
import com.alvarengadev.alvamessenger.presenter.user.login.LoginInterface
import com.alvarengadev.alvamessenger.presenter.user.login.LoginPresenter
import com.alvarengadev.alvamessenger.utils.Base64Actions
import com.alvarengadev.alvamessenger.utils.InputsValidatorUtils
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.alvarengadev.alvamessenger.utils.RoutesUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginInterface.View {

    private lateinit var loginPresenter: LoginPresenter
    private val context = this@LoginActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter = LoginPresenter(context)
        buttonLogin.setOnClickListener { checkLogin() }
    }

    private fun checkLogin() {
        val email = inputLoginEmail.editText?.text.toString()
        val password = inputLoginPassword.editText?.text.toString()

        val inputsValidatorUtils = InputsValidatorUtils(context)
        val isValidatorEmail = inputsValidatorUtils.validateEmail(inputLoginEmail, email)
        val isValidatorPassword =
            inputsValidatorUtils.validatePassword(inputLoginPassword, password)

        if (isValidatorEmail && isValidatorPassword) {
            val id = Base64Actions.encodeBase64(email)
            val user = User(id, email, password)
            loginPresenter.login(user)
        }
    }

    override fun loginSuccess(isLogin: Boolean) {
        if (isLogin) {
            startActivity(RoutesUtils.routes(context, HomeActivity::class.java))
            finish()
        }
    }

    override fun error(message: String) =
        Snackbar.make(container_login, message, Snackbar.LENGTH_SHORT).show()

    override fun saveUser(user: User) =
        PreferencesUtils(context).saveUserDatas(
            Base64Actions.encodeBase64(user.email),
            user.name
        )
}
