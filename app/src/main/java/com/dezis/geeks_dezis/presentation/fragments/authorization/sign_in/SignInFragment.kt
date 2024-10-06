package com.dezis.geeks_dezis.presentation.fragments.authorization.sign_in

import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentSignInBinding


class SignInFragment : BaseFragment<FragmentSignInBinding,SignInViewModel>(R.layout.fragment_sign_in){
    override val binding: FragmentSignInBinding by viewBinding(FragmentSignInBinding::bind)
    override val viewModel: SignInViewModel by viewModels()

    override fun constructorListeners() {
        binding.etName.addTextChangedListener { validateFields() }
        binding.etName.addTextChangedListener { validateFields() }

        binding.btnRegister.setOnClickListener{
            if (validateInputs()){
                findNavController().navigate(R.id.action_signInFragment_to_codeVerificationFragment)
            }
        }
        binding.tvRegistration.setOnClickListener{
            findNavController().navigateUp()
        }
        binding.btnContactSupport.setOnClickListener{

        }


    }
    private fun validateFields() {
        val isAllFieldsValid =
            binding.etName.text.toString().isNotEmpty() &&
            binding.etEmail.text.toString().isNotEmpty()

        if (isAllFieldsValid) {
            binding.btnRegister.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
        } else {
            binding.btnRegister.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
        }
    }



    private fun validateInputs():Boolean{
        var isValid = true
        if (binding.etName.text.toString().isEmpty()){
            binding.tilName.error = "Неверное имя/фамилия"
            isValid = false
        }else{
            binding.tilEmail.error = null
        }
        if (binding.etEmail.text.toString().isEmpty()){
            binding.tilEmail.error = "Неверный email"
            isValid = false
        }else{
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
        binding.tilEmail.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
    }

    private fun resetBorderColor() {
        binding.tilName.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
        binding.tilEmail.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
    }

}