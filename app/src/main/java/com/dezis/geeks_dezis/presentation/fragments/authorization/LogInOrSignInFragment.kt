package com.dezis.geeks_dezis.presentation.fragments.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.databinding.FragmentLoginOrSigninBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInOrSignInFragment : Fragment() {
    private var _binding: FragmentLoginOrSigninBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginOrSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogIn.setOnClickListener {
            findNavController().navigate(R.id.action_logInOrSignInFragment_to_signInFragment)
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_logInOrSignInFragment_to_authorizationFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
