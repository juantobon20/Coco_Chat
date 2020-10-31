package com.appinc.cocochat.tools

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun View.HideKeyBoard() =
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        windowToken,
        0
    )

fun EditText.Style(img: Int, padding: Int = 0) {
    this.setCompoundDrawablesWithIntrinsicBounds(img, 0, 0, 0)
    this.compoundDrawablePadding = padding
}

fun EditText.Empty(): Boolean {
    if (this.text.isEmpty()) {
        this.error = "Campo requerido"
        return true
    }
    return false
}