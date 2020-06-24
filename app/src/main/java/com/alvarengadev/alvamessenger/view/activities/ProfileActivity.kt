package com.alvarengadev.alvamessenger.view.activities

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.utils.Constants
import com.alvarengadev.alvamessenger.utils.MyPermissions
import com.alvarengadev.alvamessenger.view.bottomsheet.ProfileBottomSheet
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private lateinit var profileBottomSheet: ProfileBottomSheet
    private val myPermissions =
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)

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
        profileBottomSheet = ProfileBottomSheet(this@ProfileActivity)
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        for (resultPermission in grantResults) {
            if (resultPermission == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this@ProfileActivity, "AAAA", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
