package com.dezis.geeks_dezis.presentation.fragments.authorization

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.databinding.FragmentAdminOrUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminOrUserFragment : Fragment() {
    private lateinit var binding: FragmentAdminOrUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminOrUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Проверка подключения к интернету
        if (!isInternetAvailable(requireContext())) {
            binding.icDezis.setColorFilter(ContextCompat.getColor(requireContext(), R.color.blue))
        }

        binding.btnUser.setOnClickListener {
            findNavController().navigate(R.id.action_adminOrUserFragment_to_logInOrSignInFragment)
        }
        binding.btnAdmin.setOnClickListener {
            findNavController().navigate(R.id.action_adminOrUserFragment_to_adminSignInFragment)
        }
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}