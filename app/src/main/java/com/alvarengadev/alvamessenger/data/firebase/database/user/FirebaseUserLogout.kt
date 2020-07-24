package com.alvarengadev.alvamessenger.data.firebase.database.user

import com.alvarengadev.alvamessenger.data.firebase.settings.SettingsFirebase

class FirebaseUserLogout {
    companion object {
        fun logout() =
            SettingsFirebase.authReference.signOut()
    }
}
