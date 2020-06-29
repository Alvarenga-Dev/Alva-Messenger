package com.alvarengadev.alvamessenger.view.bottomsheet

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.alvarengadev.alvamessenger.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.profile_bottom_sheet.view.*

class ProfileBottomSheet(
    private val activity: Activity,
    private val bottomSheetInterface: BottomSheetInterface
) {

    private lateinit var bottomSheetDialog: BottomSheetDialog

    fun onCreateView() {
        val view = ViewGroup.inflate(activity, R.layout.profile_bottom_sheet, null)
        bottomSheetDialog = BottomSheetDialog(activity)

        bottomSheetDialog.setContentView(view)
        initButtons(view)
    }

    fun showDialog() = bottomSheetDialog.show()

    fun closeDialog() = bottomSheetDialog.dismiss()

    private fun initButtons(view: View) {
        view.fab_open_library.setOnClickListener(bottomSheetInterface.openLibrary())
        view.fab_open_camera.setOnClickListener(bottomSheetInterface.openCamera())
    }
}