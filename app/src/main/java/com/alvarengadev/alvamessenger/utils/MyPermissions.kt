package com.alvarengadev.alvamessenger.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MyPermissions {
    companion object {
        fun validatePermissions(
            activity: Activity,
            myPermissions: Array<String>,
            requestCode: Int
        ): Boolean {

            val listPermission = ArrayList<String>()

            if (Build.VERSION.SDK_INT >= 23) {
                for (permission in myPermissions) {
                    val isPermissionEnable = ContextCompat.checkSelfPermission(
                        activity,
                        permission
                    ) == PackageManager.PERMISSION_GRANTED
                    if (!isPermissionEnable) listPermission.add(permission)
                }

                if (listPermission.isEmpty()) return true

                val newPermissions = arrayOfNulls<String>(listPermission.size)
                listPermission.toArray(newPermissions)

                ActivityCompat.requestPermissions(activity, newPermissions, requestCode)
            }

            return true
        }
    }
}