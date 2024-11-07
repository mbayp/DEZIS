package com.dezis.geeks_dezis.presentation.fragments.admin_request

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.core.common.UiState
import com.dezis.geeks_dezis.databinding.FragmentRequestBinding
import com.dezis.geeks_dezis.presentation.fragments.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RequestFragment :
    BaseFragment<FragmentRequestBinding, RequestViewModell>(R.layout.fragment_request) {

    override val binding: FragmentRequestBinding by viewBinding(FragmentRequestBinding::bind)
    override val viewModel: RequestViewModell by viewModels()

    private val requestAdapter: RequestAdapter by lazy { RequestAdapter() }

    override fun init() {
        super.init()
        setupRecyclerView()
        setupOnBackPressedCallback()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = requestAdapter
        }
    }

    override fun launchObserver() {
        super.launchObserver()

        // Используем viewModelScope.launch, чтобы работать с корутинами
        lifecycleScope.launch {
            viewModel.getInactiveUser().collect { response ->
                // Проверка, что тело ответа не пустое
                if (response.body() != null) {
                    showError("User")
                }

                // Логирование тела ответа
                Log.e("ololo", "launchObserver: ${response.body()}")

                // Обновление адаптера с данными
                requestAdapter?.submitList(response.body())
            }


//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.inactiveUser.collect {
//                Log.e("ololo", "launchObserver: $it")
//                when (it) {
//                    is UiState.Success -> {
//                        showLoading(false)
//                        val users = it.data
//                        if (users!!.isEmpty()) {
//                            showError("Пустой ответ от сервера")
//                        } else {
//                            requestAdapter.submitList(users)
//                        }
//                        Log.e("ololo", "launchObserver: $users")
//
//                    }
//
//                    is UiState.Error -> {
//                        showError(it.mes)
//                        showLoading(false)
//                    }
//
//                    is UiState.Loading -> {
//                        Log.e("ololo", "launchObserver: Код говно", )
//                        showLoading(true)
//                    }
//                }

        }
    }

    private fun showLoading(loading: Boolean) {
        binding.progress.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun setupOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finishAffinity()
                }
            })
    }

    private fun showError(message: String) {
        Log.e("RequestFragment", message)
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
