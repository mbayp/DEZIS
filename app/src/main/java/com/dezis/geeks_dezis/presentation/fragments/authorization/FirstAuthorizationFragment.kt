package com.dezis.geeks_dezis.presentation.fragments.authorization

import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dezis.geeks_dezis.databinding.FragmentFirstAuthorizationBinding

class FirstAuthorizationFragment : BaseFragment<FragmentFirstAuthorizationBinding, FirstAuthorizationViewModel>(R.layout.fragment_first_authorization) {
    override val binding: FragmentFirstAuthorizationBinding by viewBinding(
        FragmentFirstAuthorizationBinding::bind
    )

    override val viewModel: FirstAuthorizationViewModel by viewModels()

    override fun constructorListeners() {
        binding.etName.addTextChangedListener { validateFields() }
        binding.etNum.addTextChangedListener { validateFields() }
        binding.etEmail.addTextChangedListener { validateFields() }

        binding.btnContinue.setOnClickListener {
            if (validateInputs()) {
                findNavController().navigate(R.id.action_authorizationFragment_to_secondAuthorizationFragment)
            }
        }
        binding.tvSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_authorizationFragment_to_signInFragment2)
        }
    }

    private fun validateFields() {
        val isAllFieldsValid =
            binding.etName.text.toString().isNotEmpty() &&
            binding.etNum.text.toString().isNotEmpty() &&
            binding.etEmail.text.toString().isNotEmpty()

        if (isAllFieldsValid) {
            binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
        } else {
            binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        if (binding.etName.text.toString().isEmpty()) {
            binding.tilName.error = "Неверное имя/фамилия"
            isValid = false
        } else {
            binding.tilName.error = null
        }

        if (binding.etNum.text.toString().isEmpty()) {
            binding.tilNum.error = "Неверный номер"
            isValid = false
        } else {
            binding.tilNum.error = null
        }

        if (binding.etEmail.text.toString().isEmpty()) {
            binding.tilEmail.error = "Неверный Email"
            isValid = false
        } else {
            binding.tilEmail.error = null
        }

        if (!isValid) {
            setErrorBorderColor()
        } else {
            resetBorderColor()
        }

        return isValid
    }

    private fun setErrorBorderColor() {
        binding.tilName.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
        binding.tilNum.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
        binding.tilEmail.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
    }

    private fun resetBorderColor() {
        binding.tilName.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent) // Укажите цвет по умолчанию
        binding.tilNum.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
        binding.tilEmail.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
    }
}
