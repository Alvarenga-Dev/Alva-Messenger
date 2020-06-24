package com.alvarengadev.alvamessenger.view.bottomsheet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.alvarengadev.alvamessenger.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.profile_bottom_sheet.view.*

class ProfileBottomSheet(private val context: Context) {

    private lateinit var dialog: BottomSheetDialog

    fun onCreateView() {
        val view = LayoutInflater.from(context).inflate(R.layout.profile_bottom_sheet, null, false)
        dialog = BottomSheetDialog(context)

        dialog.setContentView(view)
        initButtons(view)
    }

    fun showDialog() = dialog.show()

    private fun initButtons(view: View) {
        view.fab_open_library.setOnClickListener(onCLickFabOpenLibrary())
        view.fab_open_camera.setOnClickListener(onCLickFabOpenCamera())
    }

    private fun onCLickFabOpenLibrary(): View.OnClickListener = View.OnClickListener {
        Toast.makeText(context, "Abrir galeria", Toast.LENGTH_SHORT).show()
    }

    private fun onCLickFabOpenCamera(): View.OnClickListener = View.OnClickListener {
        Toast.makeText(context, "Abrir câmera", Toast.LENGTH_SHORT).show()
    }
}