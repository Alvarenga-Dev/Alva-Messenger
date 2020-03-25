package com.alvarengadev.alvamessenger.utils

import android.content.Context
import android.content.Intent

abstract class RoutersUtils {
    companion object{
        fun router(context: Context?, destiny: Class<*>?): Intent {
            return Intent(context, destiny)
        }
    }
}