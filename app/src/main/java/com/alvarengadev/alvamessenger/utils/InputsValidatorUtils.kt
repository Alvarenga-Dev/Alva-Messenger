package com.alvarengadev.alvamessenger.utils

import android.content.Context
import android.util.Patterns
import com.alvarengadev.alvamessenger.R
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class InputsValidatorUtils(private val context: Context) {

    companion object {
        private val NAME_PATTERN = // Name (UpperCase and LowerCase)
            Pattern.compile("^[a-zA-Z]+(([',. -][A-Za-zÀ-ú])?[A-Za-zÀ-ú]*)*\$")

        private val PASSWORD_PATTERN = // Password (UpperCase, LowerCase and Number)
            Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{6,}\$")
    }

    fun validateName(textInputLayoutFirstName: TextInputLayout, name: String): Boolean {
        return if (name.trim().isEmpty()) {
            textInputLayoutFirstName.error = this.context.getString(R.string.error_empty)
            textInputLayoutFirstName.requestFocus()
            false
        } else if (!NAME_PATTERN.matcher(name).matches()) {
            textInputLayoutFirstName.error =
                this.context.getString(R.string.error_validate_firstname_and_lastname)
            textInputLayoutFirstName.requestFocus()
            false
        } else {
            textInputLayoutFirstName.error = null
            true
        }

    }

    fun validateEmail(textInputLayoutEmail: TextInputLayout, email: String): Boolean{
        return if(email.trim().isEmpty()){
            textInputLayoutEmail.error = this.context.getString(R.string.error_empty)
            textInputLayoutEmail.requestFocus()
            false
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            textInputLayoutEmail.error = this.context.getString(R.string.error_validate_email)
            textInputLayoutEmail.requestFocus()
            false
        }else {
            textInputLayoutEmail.error = null
            true
        }
    }

    fun validatePassword(textInputLayoutPassword: TextInputLayout, password: String): Boolean{
        return if(password.trim().isEmpty()){
            textInputLayoutPassword.error = this.context.getString(R.string.error_empty)
            textInputLayoutPassword.requestFocus()
            false
        }else if(password.length < 6){
            textInputLayoutPassword.error = this.context.getString(R.string.error_validate_password_length)
            textInputLayoutPassword.requestFocus()
            false
        }else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            textInputLayoutPassword.error = this.context.getString(R.string.error_validate_password)
            textInputLayoutPassword.requestFocus()
            false
        }else {
            textInputLayoutPassword.error = null
            true
        }
    }

}