package com.example.chat.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseUtils {
    var mAut = FirebaseAuth.getInstance()
    var firebaseDatabase = FirebaseFirestore.getInstance()

}