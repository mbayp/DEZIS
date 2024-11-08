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
import com.dezis.geeks_dezis.data.remote.model.updateUserRequestModel.UpdateUserRequestModel
import com.dezis.geeks_dezis.databinding.FragmentRequestBinding
import com.dezis.geeks_dezis.presentation.fragments.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RequestFragment :
    BaseFragment<FragmentRequestBinding, RequestViewModell>(R.layout.fragment_request),
    RequestAdapter.OnItemActionListener {

    override val binding: FragmentRequestBinding by viewBinding(FragmentRequestBinding::bind)
    override val viewModel: RequestViewModell by viewModels()

    private val requestAdapter: RequestAdapter by lazy {
        RequestAdapter(this)
    }

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
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.inactiveUserState.collect { uiState ->
                Log.e("ololo", "launchObserver: $uiState")
                when (uiState) {
                    is UiState.Loading -> showLoadingIfViewAvailable(true)
                    is UiState.Success -> {
                        showLoadingIfViewAvailable(false)
                        val users = uiState.data
                        if (users.isNullOrEmpty()) {
                            showErrorIfViewAvailable("Список пользователей пуст")
                        } else {
                            requestAdapter.submitList(users)
                        }
                    }

                    is UiState.Error -> {
                        showLoadingIfViewAvailable(false)
                        showErrorIfViewAvailable(uiState.mes)
                    }
                }
            }
        }
    }

    private fun showLoadingIfViewAvailable(isLoading: Boolean) {
        if (view != null && isAdded) {
            showLoading(isLoading)
        }
    }

    private fun showErrorIfViewAvailable(message: String) {
        if (view != null && isAdded) {
            showError(message)
        }
    }

    override fun onConfirmClicked(userId: Int, model: UpdateUserRequestModel) {
        viewModel.updateUser(userId, model)  // Подтверждение пользователя
    }

    override fun onDeleteClicked(userId: Int) {
        viewModel.deleteUser(userId)  // Удаление пользователя
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

    private fun showLoading(loading: Boolean) {
        binding.progress.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun showError(message: String) {
        Log.e("RequestFragment", message)
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
