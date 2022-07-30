package com.example.cryptos

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.fragment.app.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder

inline fun <reified T : Fragment> FragmentActivity.replaceFragment(addToBackStack: Boolean = false) {
    supportFragmentManager.commit {
        if (addToBackStack) addToBackStack(null)
        replace<T>(R.id.fragmentContainerView)
    }
}

fun Activity.showOptionsDialog(
    title: String = "",
    cancelable: Boolean = false,
    items: Array<String>,
    selectedItemAction: (selectedItemIndex: Int) -> Unit = {}
) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setItems(items) { dialog: DialogInterface, index: Int ->
            selectedItemAction.invoke(index)
            dialog.dismiss()
        }
        .setCancelable(cancelable)
        .show()
}

fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}