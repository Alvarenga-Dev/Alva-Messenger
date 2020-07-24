package com.alvarengadev.alvamessenger.data.firebase.settings

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class SettingsFirebase {
    companion object {
        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
        val authReference: FirebaseAuth = FirebaseAuth.getInstance()
        val storageReference: StorageReference = FirebaseStorage.getInstance().reference
    }
}