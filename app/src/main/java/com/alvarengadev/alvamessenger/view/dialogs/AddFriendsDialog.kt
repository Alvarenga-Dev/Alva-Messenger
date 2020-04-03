package com.alvarengadev.alvamessenger.view.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatDialogFragment
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.providers.friend.AddFriend
import com.alvarengadev.alvamessenger.utils.InputsValidatorUtils
import com.google.android.material.textfield.TextInputLayout

class AddFriendsDialog : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(this.activity)
        val layoutInflater = activity?.layoutInflater
        val view = layoutInflater?.inflate(R.layout.dialog_add_friends, null)
        builder.setView(view)

        val btnDismiss = view!!.findViewById(R.id.btnDismissAddFriendsDialog) as Button
        val btnAddFriends = view.findViewById(R.id.btnAddFriendsDialog) as Button
        val inputEmail = view.findViewById(R.id.inputAddFriendEmail) as TextInputLayout

        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnDismiss.setOnClickListener{ dismissDialog(dialog) }
        btnAddFriends.setOnClickListener{ addFriend(inputEmail, inputEmail.editText?.text.toString()) }

        return dialog
    }

    private fun dismissDialog(dialog: AlertDialog) {
        dialog.dismiss()
    }

    private fun addFriend(inputEmail: TextInputLayout, email: String) {
        val inputsValidatorUtils = InputsValidatorUtils(context!!)
        if ( inputsValidatorUtils.validateEmail(inputEmail, email) ) {
            val addFriend = AddFriend(context!!, email)
            addFriend.add()
            dialog?.dismiss()
        }

    }
}
