package com.alvarengadev.alvamessenger.data.firebase.database.user

import com.alvarengadev.alvamessenger.data.firebase.settings.SettingsFirebase
import com.alvarengadev.alvamessenger.utils.Constants
import com.google.firebase.storage.UploadTask

class FirebaseUserImageProfile {
    companion object {
        fun saveImage(id: String, byteDataImageProfile: ByteArray): UploadTask {
            val storageReference = SettingsFirebase.storageReference
            storageReference
                .child(Constants.FIREBASE_STORAGE_CHILD_IMAGES)
                .child(Constants.FIREBASE_STORAGE_CHILD_PROFILES)
                .child("$id.jpeg")

            return storageReference.putBytes(byteDataImageProfile)
        }
    }
}