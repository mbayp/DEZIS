package com.dezis.geeks_dezis.presentation.fragments.profile.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.common.UiState
import com.dezis.geeks_dezis.databinding.FragmentOrderHistoryBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class OrderHistory @Inject constructor() : Fragment() {

    private var _binding: FragmentOrderHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OrderHistoryViewModel by viewModels()
    private var adapter = OrderHistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        launchObserver()
        setupListeners()
    }

    private fun setupListeners() {
        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_history_to_profile)
        }
    }

    private fun init() {
        binding.rvAllServices.adapter = adapter
        binding.rvAllServices.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun launchObserver() {
        viewModel.firstTreatmentState.onEach { state ->
            when (state) {
                is UiState.Success -> {
                    val treatment = state.data
                    binding.tvClientName.text = treatment.clientName
                    binding.tvService.text = treatment.service
                    binding.tvAddress.text = treatment.address
                    binding.tvHouseNumber.text = treatment.houseNumber
                    binding.tvDate.text = treatment.date
                    binding.tvTime.text = treatment.time
                }

                is UiState.Error -> {
                    Snackbar.make(binding.root, state.mes, Snackbar.LENGTH_LONG).show()
                }

                is UiState.Loading -> {
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.allTreatmentsState.onEach { state ->
            when (state) {
                is UiState.Success -> {
                    adapter.submitList(state.data)
                }

                is UiState.Error -> {
                    Snackbar.make(binding.root, state.mes, Snackbar.LENGTH_LONG).show()
                }

                is UiState.Loading -> {
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}