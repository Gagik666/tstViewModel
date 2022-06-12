package com.example.chat.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

fun Fragment.openFragment(id: Int) {
    findNavController().navigate(id)
}



fun Fragment.shouToast(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun AndroidViewModel.shouToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.isRegistred(): Boolean {
    return FirebaseAuth.getInstance().currentUser != null
}