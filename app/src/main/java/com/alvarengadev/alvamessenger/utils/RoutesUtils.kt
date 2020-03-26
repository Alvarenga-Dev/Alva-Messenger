package com.alvarengadev.alvamessenger.utils

import android.content.Context
import android.content.Intent

abstract class RoutesUtils {
    companion object{
        fun routes(context: Context?, destiny: Class<*>?): Intent {
            return Intent(context, destiny)
        }
    }
}