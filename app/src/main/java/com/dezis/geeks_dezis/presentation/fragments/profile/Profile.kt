package com.dezis.geeks_dezis.presentation.fragments.profile

import android.text.InputType
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Profile @Inject constructor() :
    BaseFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {
    override val binding by lazy { FragmentProfileBinding.bind(requireView()) }

    override val viewModel: ProfileViewModel by viewModels()

    private var isDataChangedByUser: Boolean = false

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
        binding.imgHistory.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_history)
        }

        binding.editPhoneNumber.setOnClickListener {
            showPhoneNumberDialog()
        }

        observeViewModel()
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

    private fun observeViewModel() {
        viewModel.phoneNumber.observeUIState(
            success = {
                binding.tvPhoneNumber.text = it
                if (isDataChangedByUser) {
                    showCustomDialog()
                    isDataChangedByUser = false
                }
            },
            error = { showToast("Ошибка обновления номера телефона: $it") }
        )

        viewModel.avatar.observeUIState(
            success = {
                if (isDataChangedByUser) {
                    showCustomDialog()
                    isDataChangedByUser = false
                }
            },
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
                isDataChangedByUser = true
            }
        }
        dialog.setNegativeButton("Отмена", null)
        dialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
