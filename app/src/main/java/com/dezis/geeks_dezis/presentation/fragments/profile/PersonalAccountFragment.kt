package com.dezis.geeks_dezis.presentation.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.databinding.FragmentPersonalAccountBinding


class PersonalAccountFragment : Fragment() {

    private val binding by lazy {
        FragmentPersonalAccountBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigate()
    }

    private fun navigate() {

        binding.profile.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_profileFragment)
        }

        binding.about.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_aboutUsFragment)
        }

        binding.settings.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_settingsFragment)
        }
    }
}
