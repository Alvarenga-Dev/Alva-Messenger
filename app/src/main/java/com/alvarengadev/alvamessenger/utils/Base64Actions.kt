package com.alvarengadev.alvamessenger.utils

import android.util.Base64

class Base64Actions {
    companion object {
        fun encodeBase64(email: String): String =
            Base64.encodeToString(email.toByteArray(), Base64.DEFAULT)
                .replace("([\\n\\r])".toRegex(), "")

        fun decodeBase64(codeEmail: String): String =
            String(Base64.decode(codeEmail, Base64.DEFAULT))
    }
}
