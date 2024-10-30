package com.dezis.geeks_dezis.presentation.fragments.profile

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
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
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        setupListeners()
        observeViewModel()
        loadSavedData()
        viewModel.fetchUserData(userId = 1)
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

                sharedPreferences.edit()
                    .putString(SharedPreferencesKeys.PHONE_NUMBER, newPhoneNumber).apply()
                showToast("Phone number updated")
            } else {
                showToast("Invalid phone number format")
            }
        }
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.isNotEmpty() && phoneNumber.length in 7..15
    }

    private fun observeViewModel() {
        viewModel.avatar.observeUIState(
            success = { newAvatarUri ->
                if (isDataChangedByUser) showCustomDialog()
                sharedPreferences.edit().putString(SharedPreferencesKeys.AVATAR_URI, newAvatarUri)
                    .apply()
            },
            error = { showToast("Error loading avatar: $it") }
        )

        viewModel.phoneNumber.observeUIState(
            success = { newPhoneNumber ->
                if (isDataChangedByUser) showCustomDialog()
                sharedPreferences.edit()
                    .putString(SharedPreferencesKeys.PHONE_NUMBER, newPhoneNumber).apply()
            },
            error = { showToast("Error loading phone: $it") }
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
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