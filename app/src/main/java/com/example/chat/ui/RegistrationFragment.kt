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
import com.example.chat.databinding.FragmentRegistrationBinding
import com.example.chat.extensions.openFragment

class RegistrationFragment : Fragment() {
    lateinit var binding: FragmentRegistrationBinding
    lateinit var authenticationViewModel: AuthenticationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticationViewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
        binding.tvLogIn.setOnClickListener {
            openFragment(R.id.action_registrationFragment_to_loginFragment)
        }

        binding.btnRegistration.setOnClickListener {
            authenticationViewModel.registration(
                binding.edFirstName.text.toString(),
                binding.edLastName.text.toString(),
                binding.edEmail.text.toString(),
                binding.edPassword.text.toString()
            )
        }
        authenticationViewModel.isRegisterted.observe(viewLifecycleOwner, Observer {
            if (it) openFragment(R.id.action_registrationFragment_to_startFragment)
        })
    }
}