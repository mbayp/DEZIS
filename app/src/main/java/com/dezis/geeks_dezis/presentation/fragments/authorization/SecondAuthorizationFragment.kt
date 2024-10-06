package com.dezis.geeks_dezis.presentation.fragments.authorization

import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentSecondAuthorizationBinding

class SecondAuthorizationFragment:BaseFragment<FragmentSecondAuthorizationBinding,SecondAuthorizationViewModel>(R.layout.fragment_second_authorization){
    override val binding: FragmentSecondAuthorizationBinding by viewBinding(FragmentSecondAuthorizationBinding::bind)
    override val viewModel: SecondAuthorizationViewModel by viewModels()

    override fun constructorListeners() {
        binding.etAddress.addTextChangedListener { validateFields() }
        binding.etNumFlat.addTextChangedListener { validateFields() }
        binding.cbPersonalData.setOnCheckedChangeListener { _, _ -> validateFields() }
        binding.cbPrivacyPolicy.setOnCheckedChangeListener { _, _ -> validateFields() }
        binding.btnRegister.setOnClickListener {
            if (validateInputs()) {
                findNavController().navigate(R.id.action_secondAuthorizationFragment_to_codeVerificationFragment)
            }
        }
        binding.tvContactSupport.setOnClickListener {
        }
    }
    private fun validateFields() {
        val isAllFieldsValid =
            binding.etAddress.text.toString().isNotEmpty() &&
            binding.etNumFlat.text.toString().isNotEmpty()&&
            binding.cbPersonalData.isChecked&&
            binding.cbPrivacyPolicy.isChecked
        if (isAllFieldsValid) {
            binding.btnRegister.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
        } else {
            binding.btnRegister.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
        }

    }
    private fun validateInputs(): Boolean {
        var isValid = true
        if (binding.etAddress.text.toString().isEmpty()) {
            binding.tilAddress.error = "Неверный номер"
            isValid = false
        } else {
            binding.tilAddress.error = null
        }

        if (binding.etNumFlat.text.toString().isEmpty()) {
            binding.tilNumFlat.error = "Неверный Email"
            isValid = false
        } else {
            binding.tilNumFlat.error = null
        }
        if (!isValid) {
            setErrorBorderColor()
        } else {
            resetBorderColor()
        }
        return isValid
    }
    private fun setErrorBorderColor() {
        binding.tilNumOrd.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
        binding.tilAddress.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
        binding.tilNumFlat.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
    }
    private fun resetBorderColor() {
        binding.tilAddress.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
        binding.tilNumOrd.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
        binding.tilNumFlat.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
    }
}