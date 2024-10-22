package com.alvarengadev.alvamessenger.view.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.presenter.user.profile.ProfileInterface
import com.alvarengadev.alvamessenger.presenter.user.profile.ProfilePresenter
import com.alvarengadev.alvamessenger.utils.Constants
import com.alvarengadev.alvamessenger.utils.MyPermissions
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.alvarengadev.alvamessenger.view.bottomsheet.BottomSheetInterface
import com.alvarengadev.alvamessenger.view.bottomsheet.ProfileBottomSheet
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.ByteArrayOutputStream

class ProfileActivity : AppCompatActivity(), BottomSheetInterface, ProfileInterface.View {

    private lateinit var profileBottomSheet: ProfileBottomSheet
    private val myPermissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )
    private val context = this@ProfileActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initView()
        initPermissions()
        initBottomSheet()
    }

    private fun initView() =
        fab_change_photo.setOnClickListener(onClickOpenBottomSheet())

    private fun initBottomSheet() {
        profileBottomSheet = ProfileBottomSheet(context, this)
        profileBottomSheet.onCreateView()
    }

    private fun onClickOpenBottomSheet(): View.OnClickListener = View.OnClickListener {
        profileBottomSheet.showDialog()
    }

    private fun initPermissions() =
        MyPermissions.validatePermissions(
            context,
            myPermissions,
            Constants.REQUEST_PERMISSION_ENABLE
        )

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.REQUEST_LIBRARY) {
                val selectedImageUri = data?.data
                iv_user_photo.setImageURI(selectedImageUri)
            } else if (requestCode == Constants.REQUEST_CAMERA) {
                val bitmap = data?.extras.let {
                    it?.get("data")
                } as Bitmap
                iv_user_photo.setImageBitmap(bitmap)
            }

            val bitmap = (iv_user_photo.drawable as BitmapDrawable).bitmap
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream)
            val byteDataImage = byteArrayOutputStream.toByteArray()

            val preferences = PreferencesUtils(context)
            val id = preferences.getUserKey()!!

            val profilePresenter = ProfilePresenter(this)
            profilePresenter.saveImageProfile(id, byteDataImage)

            profileBottomSheet.closeDialog()
        }
    }

    override fun openCamera(): View.OnClickListener = View.OnClickListener {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Constants.REQUEST_CAMERA)
    }

    override fun openLibrary(): View.OnClickListener = View.OnClickListener {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, Constants.REQUEST_LIBRARY)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        for (resultPermission in grantResults) {
            if (resultPermission == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(context, "Você precisa dar as permissões", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun toastMessage(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
