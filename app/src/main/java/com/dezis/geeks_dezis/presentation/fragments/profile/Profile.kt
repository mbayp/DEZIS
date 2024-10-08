package com.dezis.geeks_dezis.presentation.fragments.profile

import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentProfileBinding
import com.dezis.geeks_dezis.presentation.fragments.profile.history.ProfileViewModel

class Profile : BaseFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {

    override val binding by lazy { FragmentProfileBinding.bind(requireView()) }
    override val viewModel: ProfileViewModel by lazy { ProfileViewModel() }

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                binding.imgAvatar.setImageURI(it)
                viewModel.updateAvatar(it.toString())
            }
        }

    override fun init() {
        super.init()
        binding.imgHistory.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_history)
        }

        binding.imgEdit.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.editPhoneNumber.setOnClickListener {
            showPhoneNumberDialog()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.phoneNumber.observeUIState(
            success = { binding.tvPhoneNumber.text = it },
            error = { showToast("Ошибка обновления номера телефона: $it") }
        )

        viewModel.avatar.observeUIState(
            success = {},
            error = { showToast("Ошибка загрузки аватара: $it") }
        )
    }

    private fun showPhoneNumberDialog() {
        val dialog = AlertDialog.Builder(requireContext())
        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_PHONE
        dialog.setView(input)
        dialog.setTitle("Изменить номер телефона")
        dialog.setPositiveButton("Сохранить") { _, _ ->
            val newPhoneNumber = input.text.toString()
            if (newPhoneNumber.isNotEmpty()) {
                viewModel.updatePhoneNumber(newPhoneNumber)
            }
        }
        dialog.setNegativeButton("Отмена", null)
        dialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}