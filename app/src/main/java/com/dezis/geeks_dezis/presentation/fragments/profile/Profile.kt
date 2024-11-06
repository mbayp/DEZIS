package com.dezis.geeks_dezis.presentation.fragments.profile

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.core.common.SharedPreferencesKeys
import com.dezis.geeks_dezis.core.common.UiState
import com.dezis.geeks_dezis.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class Profile @Inject constructor() :
    BaseFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {

    private lateinit var sharedPreferences: SharedPreferences
    private var isDataChangedByUser: Boolean = false

    override val binding by lazy { FragmentProfileBinding.bind(requireView()) }
    override val viewModel: ProfileViewModel by viewModels()

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                binding.imgAvatar.setImageURI(it)
                viewModel.updateAvatar(it.toString())
                isDataChangedByUser = true
            }
        }

    override fun init() {
        super.init()
        sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        setupListeners()
        observeViewModel()
        loadSavedData()
        viewModel.fetchUserData(userId = 0)
    }

    private fun setupListeners() {
        binding.imgHistory.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_history)
        }

        binding.editPhoneNumber.setOnClickListener {
            showPhoneNumberEditText()
        }

        binding.imgAvatar.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }
    }

    private fun loadSavedData() {
        val savedUsername = sharedPreferences.getString("username", "")
        val savedEmail = sharedPreferences.getString("email", "")

        binding.tvFullName.text = savedUsername
        binding.tvEmail.text = savedEmail

        sharedPreferences.getString(SharedPreferencesKeys.PHONE_NUMBER, null)
            ?.let { savedPhoneNumber ->
                binding.etPhoneNumber.setText(savedPhoneNumber)
            }
        sharedPreferences.getString(SharedPreferencesKeys.AVATAR_URI, null)?.let { savedAvatarUri ->
            Glide.with(this)
                .load(savedAvatarUri)
                .placeholder(R.drawable.ic_profile2)
                .into(binding.imgAvatar)
        }
    }

    private fun showPhoneNumberEditText() {
        binding.etPhoneNumber.visibility = View.VISIBLE
        binding.editPhoneNumber.visibility = View.VISIBLE

        binding.editPhoneNumber.setOnClickListener {
            val newPhoneNumber = binding.etPhoneNumber.text.toString()
            if (isValidPhoneNumber(newPhoneNumber)) {
                viewModel.updatePhoneNumber(newPhoneNumber)
                isDataChangedByUser = true
                showCustomDialog()
            } else {
                Toast.makeText(context, "Неверный номер телефона", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.length == 10 && phoneNumber.all { it.isDigit() }
    }


    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.userData.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val userData = state.data
                        binding.tvFullName.text = userData.username ?: "Username not available"
                        binding.tvEmail.text = userData.email ?: "Email not available"
                        binding.etPhoneNumber.setText(userData.number ?: "")
                        Glide.with(this@Profile)
                            .load(userData.avatar ?: R.drawable.ic_profile2)
                            .placeholder(R.drawable.ic_profile2)
                            .into(binding.imgAvatar)
                    }

                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), "Ошибка: ${state.mes}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun showCustomDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog, null)
        val dialog = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
            .setView(dialogView)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        dialog.window?.decorView?.postDelayed({
            dialog.dismiss()
        }, 2000)
    }

}