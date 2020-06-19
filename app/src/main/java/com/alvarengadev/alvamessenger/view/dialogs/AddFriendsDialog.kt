package com.alvarengadev.alvamessenger.view.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatDialogFragment
import com.alvarengadev.alvamessenger.R
import com.alvarengadev.alvamessenger.presenter.friend.add.AddFriendInterface
import com.alvarengadev.alvamessenger.presenter.friend.add.AddFriendPresenter
import com.alvarengadev.alvamessenger.utils.InputsValidatorUtils
import com.alvarengadev.alvamessenger.utils.PreferencesUtils
import com.google.android.material.textfield.TextInputLayout

class AddFriendsDialog : AppCompatDialogFragment(), AddFriendInterface.View {

    private lateinit var inputEmail: TextInputLayout

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(this.activity)
        val layoutInflater = activity?.layoutInflater
        val view = layoutInflater?.inflate(R.layout.dialog_add_friends, null)!!
        builder.setView(view)

        val btnDismiss = view.findViewById(R.id.btnDismissAddFriendsDialog) as Button
        val btnAddFriends = view.findViewById(R.id.btnAddFriendsDialog) as Button
        inputEmail = view.findViewById(R.id.inputAddFriendEmail) as TextInputLayout

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
        if (inputsValidatorUtils.validateEmail(inputEmail, email)) {
            AddFriendPresenter(this, email).addFriend()
        }
    }

    override fun getUserKey(): String? =
        PreferencesUtils(context!!).getUserKey()

    override fun error(message: String) {
        Log.e("Usuário", "Não existe! $message")
        inputEmail.error = message
    }

    override fun closeDialog(isAdd: Boolean) {
        if (isAdd) dialog?.dismiss()
    }

}
