package com.dezis.geeks_dezis.presentation.fragments.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.databinding.FragmentAdminOrUserBinding

class AdminOrUserFragment:Fragment() {
    private val binding by lazy {
        FragmentAdminOrUserBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnUser.setOnClickListener {
            findNavController().navigate(R.id.action_adminOrUserFragment_to_logInOrSignInFragment)
        }
        binding.btnAdmin.setOnClickListener {
            findNavController().navigate(R.id.action_adminOrUserFragment_to_adminSignInFragment)
        }
    }

}