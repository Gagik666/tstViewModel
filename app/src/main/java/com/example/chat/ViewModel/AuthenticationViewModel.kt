package com.example.chat.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chat.extensions.shouToast
import com.example.chat.model.UserModel
import com.example.chat.utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {

    val isRegisterted = MutableLiveData<Boolean>()
    val isLogIn = MutableLiveData<Boolean>()
    private var _user = MutableLiveData<UserModel>()
    var user: LiveData<UserModel> = _user



    fun getUserData() {
        FirebaseUtils().firebaseDatabase.collection("users")
            .document(FirebaseAuth.getInstance().uid!!).get()
            .addOnSuccessListener { Task ->
                _user.value = UserModel(
                    "FirsName: ${Task.data!!["firstName"]}",
                    "LastName:  ${Task.data!!["lastName"]}",
                    "Email: ${Task.data!!["email"]}",
                    "Password: ${Task.data!!["password"]}"
                )
            }
    }

    fun logIn(email: String, password: String) {
        FirebaseUtils().mAut.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                shouToast(getApplication(), "log in ok")
                isLogIn.value = true
            }
        }
    }

    fun registration(firestName: String, lastName: String, email: String, password: String) {
        FirebaseUtils().mAut.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { Task ->
                if (Task.isSuccessful) {
                    shouToast(getApplication(), "registration ok")
                    creatUser(firestName, lastName, email, password)
                    isRegisterted.value = true
                }
            }
    }

    fun creatUser(firestName: String, lastName: String, email: String, password: String) {
        val hashMap = hashMapOf<String, Any>(
            "firstName" to firestName,
            "lastName" to lastName,
            "email" to email,
            "password" to password,
        )
        FirebaseUtils().firebaseDatabase.collection("users")
            .document(FirebaseAuth.getInstance().uid!!)
            .set(hashMap).addOnCompleteListener {
                if (it.isSuccessful) {
                    shouToast(getApplication(), "add data ok")
                }
            }
    }

    fun logOut() {
        FirebaseAuth.getInstance().signOut()
    }

}