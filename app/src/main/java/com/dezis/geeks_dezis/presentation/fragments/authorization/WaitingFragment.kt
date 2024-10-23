package com.dezis.geeks_dezis.presentation.fragments.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.databinding.FragmentWaitingPermissionBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WaitingFragment:Fragment() {
    private val binding by lazy {
        FragmentWaitingPermissionBinding.inflate(layoutInflater)
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
        lifecycleScope.launch {
            delay(10000)
            //findNavController().navigate(R.id.action_waitingFragment_to_homeFragment)
        }


    }
}