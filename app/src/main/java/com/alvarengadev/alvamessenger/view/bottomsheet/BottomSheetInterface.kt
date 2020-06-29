package com.alvarengadev.alvamessenger.view.bottomsheet

import android.view.View

interface BottomSheetInterface {
    fun openCamera(): View.OnClickListener
    fun openLibrary(): View.OnClickListener
}