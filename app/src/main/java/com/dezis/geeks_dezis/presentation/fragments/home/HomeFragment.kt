package com.dezis.geeks_dezis.presentation.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentHomeBinding
import com.dezis.geeks_dezis.presentation.fragments.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    override val viewModel: HomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation()
    }

    private fun navigation() = with(binding) {
        btnDetailsDeratization.setOnClickListener{
            findNavController().navigate(R.id.serviceScreenFragment)
        }
        btnDetailsDisinfection.setOnClickListener{
            findNavController().navigate(R.id.serviceScreenFragment)
        }
        btnDetailsDisinsection.setOnClickListener{
            findNavController().navigate(R.id.serviceScreenFragment)
        }
    }

}