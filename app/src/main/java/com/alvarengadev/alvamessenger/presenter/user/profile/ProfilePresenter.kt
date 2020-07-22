package com.alvarengadev.alvamessenger.presenter.user.profile

import android.util.Log
import com.alvarengadev.alvamessenger.data.firebase.database.user.FirebaseUserImageProfile

class ProfilePresenter(private val view: ProfileInterface.View) : ProfileInterface.Presenter {

    override fun saveImageProfile(id: String, byteDataImageProfile: ByteArray) {

        FirebaseUserImageProfile.saveImage(id, byteDataImageProfile)
            .addOnSuccessListener {
                view.toastMessage("Sucesso ao salvar")
            }.addOnFailureListener {
                view.toastMessage("Erro ao salvar")
                Log.e("Error", it.toString())
            }
    }

}