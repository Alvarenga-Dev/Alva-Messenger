package com.alvarengadev.alvamensseger.presenters

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

abstract class SettingsFirebase{
    companion object {

        private var databaseReference: DatabaseReference? = null
        private var authReference: FirebaseAuth? = null

        fun databaseReference(): DatabaseReference? {
            if (databaseReference == null) {
                databaseReference = FirebaseDatabase.getInstance().reference
            }
            return databaseReference
        }

        fun authReference(): FirebaseAuth? {
            if (authReference == null) {
                authReference = FirebaseAuth.getInstance()
            }
            return authReference
        }

    }

}