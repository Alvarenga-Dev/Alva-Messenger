package com.alvarengadev.alvamessenger.view.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.utils.Constants
import com.alvarengadev.alvamessenger.utils.MyPermissions
import com.alvarengadev.alvamessenger.view.bottomsheet.BottomSheetInterface
import com.alvarengadev.alvamessenger.view.bottomsheet.ProfileBottomSheet
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), BottomSheetInterface {

    private lateinit var profileBottomSheet: ProfileBottomSheet
    private val myPermissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

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
        profileBottomSheet = ProfileBottomSheet(this@ProfileActivity, this)
        profileBottomSheet.onCreateView()
    }

    private fun onClickOpenBottomSheet(): View.OnClickListener = View.OnClickListener {
        profileBottomSheet.showDialog()
    }

    private fun initPermissions() {
        MyPermissions.validatePermissions(
            this@ProfileActivity,
            myPermissions,
            Constants.REQUEST_PERMISSION_ENABLE
        )
    }

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
                Toast.makeText(
                    this@ProfileActivity,
                    "Você precisa dar as permissões",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }

    }
}
