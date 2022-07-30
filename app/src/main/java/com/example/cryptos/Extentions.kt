package com.example.cryptos

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.*

inline fun <reified T : Fragment> FragmentActivity.addFragment(addToBackStack: Boolean = false) {
//    supportFragmentManager.commit {
//        if (addToBackStack) addToBackStack(null)
//        add<T>(R.id.fragmentContainerView)
//    }
}

fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}