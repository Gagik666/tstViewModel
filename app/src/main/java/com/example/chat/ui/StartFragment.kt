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
import com.example.chat.databinding.FragmentStartBinding
import com.example.chat.extensions.isRegistred
import com.example.chat.extensions.openFragment

class StartFragment : Fragment() {
    lateinit var binding: FragmentStartBinding
    lateinit var authenticationViewModel: AuthenticationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticationViewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]

        if (isRegistred()) {
            authenticationViewModel.getUserData()
        }

        authenticationViewModel.user.observe(viewLifecycleOwner, Observer {
            binding.tvFirstName.text = it.firstName
            binding.tvLastName.text = it.lastName
            binding.tvEmail.text = it.email
            binding.tvPassword.text = it.password
        })

        if (!isRegistred()) {
            openFragment(R.id.action_startFragment_to_loginFragment)
        }

        binding.btnLogOut.setOnClickListener {
            authenticationViewModel.logOut()
            openFragment(R.id.action_startFragment_to_loginFragment)
        }
    }


}