package com.dezis.geeks_dezis.presentation.fragments.authorization

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentCodeVerificationBinding
import com.dezis.geeks_dezis.presentation.fragments.viewBinding

class CodeVerificationFragment : BaseFragment<FragmentCodeVerificationBinding,CodeVerificationViewModel>(R.layout.fragment_code_verification) {

    override val binding: FragmentCodeVerificationBinding by viewBinding(FragmentCodeVerificationBinding::bind)

    override val viewModel: CodeVerificationViewModel by viewModels()

    override fun constructorListeners() {
        val editTexts = listOf(binding.etDigit1, binding.etDigit2, binding.etDigit3, binding.etDigit4)

        editTexts.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && index < editTexts.size - 1) {
                        editTexts[index + 1].requestFocus()
                    }
                    else if (s.isNullOrEmpty() && index > 0) {
                        editTexts[index - 1].requestFocus()
                    }

                    checkIfCodeEntered()
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }

        binding.btnContinue.setOnClickListener {
            val enteredCode = editTexts.joinToString("") { it.text.toString() }
            val correctCode = "1234"

            if (enteredCode == correctCode) {
                Toast.makeText(requireContext(), "Правильный код", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_codeVerificationFragment_to_successfulVerificationFragment)
            } else {
                showError()
            }
        }
    }

    private fun checkIfCodeEntered() {
        val isCodeComplete =
            binding.etDigit1.text.isNotEmpty() &&
            binding.etDigit2.text.isNotEmpty() &&
            binding.etDigit3.text.isNotEmpty() &&
            binding.etDigit4.text.isNotEmpty()

        binding.btnContinue.isEnabled = isCodeComplete
        binding.btnContinue.backgroundTintList = if (isCodeComplete) {
            ContextCompat.getColorStateList(requireContext(), R.color.blue)
        } else {
            ContextCompat.getColorStateList(requireContext(), R.color.grey)
        }
    }

    private fun showError() {
        binding.tvError.visibility = View.VISIBLE
        binding.etDigit1.background = ContextCompat.getDrawable(requireContext(), R.drawable.otp_edittext_error_background)
        binding.etDigit2.background = ContextCompat.getDrawable(requireContext(), R.drawable.otp_edittext_error_background)
        binding.etDigit3.background = ContextCompat.getDrawable(requireContext(), R.drawable.otp_edittext_error_background)
        binding.etDigit4.background = ContextCompat.getDrawable(requireContext(), R.drawable.otp_edittext_error_background)
    }

}