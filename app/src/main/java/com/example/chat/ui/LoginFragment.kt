package com.example.chat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.ViewModel.AuthenticationViewModel
import com.example.chat.databinding.FragmentLoginBinding
import com.example.chat.extensions.openFragment
import com.example.chat.extensions.shouToast
import com.example.chat.utils.FirebaseUtils

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var authenticationViewModel: AuthenticationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticationViewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
        binding.tvRegistration.setOnClickListener {
            openFragment(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.btnLogIn.setOnClickListener {
             authenticationViewModel.logIn(
                 binding.edEmail.text.toString(),
                 binding.edPassword.text.toString()
             )
        }

        authenticationViewModel.isLogIn.observe(viewLifecycleOwner, Observer {
            if (it) openFragment(R.id.action_loginFragment_to_startFragment)
        })
    }

}