package com.alvarengadev.alvamessenger.utils

import android.util.Base64

abstract class Base64Actions {

    companion object {
        fun encodeBase64(email: String) : String {
            return Base64.encodeToString(email.toByteArray(), Base64.DEFAULT).replace("([\\n\\r])".toRegex(), "")
        }

        fun decodeBase64(codeEmail: String) : String {
            return String( Base64.decode(codeEmail, Base64.DEFAULT) )
        }
    }

}