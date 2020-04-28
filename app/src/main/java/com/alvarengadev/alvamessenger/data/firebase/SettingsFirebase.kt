package com.alvarengadev.alvamessenger.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

abstract class SettingsFirebase{
    companion object {
        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
        val authReference: FirebaseAuth = FirebaseAuth.getInstance()
    }
}