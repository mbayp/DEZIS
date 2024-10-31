package com.dezis.geeks_dezis.presentation.fragments.authorization

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dezis.geeks_dezis.R
import com.dezis.geeks_dezis.core.base.BaseFragment
import com.dezis.geeks_dezis.databinding.FragmentFirstAuthorizationBinding
import by.kirich1409.viewbindingdelegate.viewBinding

class FirstAuthorizationFragment : BaseFragment<FragmentFirstAuthorizationBinding, FirstAuthorizationViewModel>(R.layout.fragment_first_authorization) {
    override val binding: FragmentFirstAuthorizationBinding by viewBinding(FragmentFirstAuthorizationBinding::bind)
    override val viewModel: FirstAuthorizationViewModel by viewModels()

    override fun constructorListeners() {
        binding.etName.addTextChangedListener { validateFields() }
        binding.etPassword.addTextChangedListener { validateFields() }
        binding.etEmail.addTextChangedListener { validateFields() }
        binding.btnContinue.setOnClickListener {
            if (validateInputs()) {
                val username = binding.etName.text.toString()
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()

                val action = FirstAuthorizationFragmentDirections.actionAuthorizationFragmentToSecondAuthorizationFragment(
                    userName = username,
                    email = email,
                    password = password
                )
                findNavController().navigate(action)
            }
        }
        // setupClickableText()
    }

    private fun validateFields() {
        val isAllFieldsValid = binding.etName.text.toString().isNotEmpty() &&
                binding.etPassword.text.toString().isNotEmpty() &&
                binding.etEmail.text.toString().isNotEmpty()

        if (isAllFieldsValid) {
            binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_dark))
        } else {
            binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.true_gray))
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        if (binding.etName.text.toString().isEmpty()) {
            binding.tilName.error = " "
            isValid = false
        } else {
            binding.tilName.error = null
        }
        if (binding.etEmail.text.toString().isEmpty()) {
            binding.tilEmail.error = " "
            isValid = false
        } else if (!isEmailValid(binding.etEmail.text.toString())) {
            binding.tilEmail.error = "Введите корректный адрес электронной почты"
            isValid = false
        } else {
            binding.tilEmail.error = null
        }
        if (binding.etPassword.text.toString().isEmpty()) {
            binding.tilPassword.error = " "
            isValid = false
        } else {
            val password = binding.etPassword.text.toString()
            if (!isPasswordValid(password)) {
                binding.tilPassword.error = "Пароль должен содержать заглавные и строчные буквы, цифры и иметь длину не менее 8 символов"
                isValid = false
            } else {
                binding.tilPassword.error = null
            }
        }
        return isValid
    }

    private fun isPasswordValid(password: String): Boolean {
        val hasUppercase = password.any { it.isUpperCase() }
        val hasLowercase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val isValidLength = password.length >= 8
        return hasUppercase && hasLowercase && hasDigit && isValidLength
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun setupClickableText() {
        val termsTextView = binding.termsOfSale
        val spannableString = SpannableString(getString(R.string.policy_txt))

        val salesTermsClickable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val salesTerms = "https://youtu.be/Ca8YSrtxI3s?si=7k_pwqneUTWDR-cD"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(salesTerms))
                startActivity(intent)
            }
        }

        val privacyPolicyClickable = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val privacyPolicy = "https://youtu.be/Ca8YSrtxI3s?si=7k_pwqneUTWDR-cD"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(privacyPolicy))
                startActivity(intent)
            }
        }

        spannableString.setSpan(salesTermsClickable, 63, 80, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.blue)), 63, 80, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        spannableString.setSpan(privacyPolicyClickable, 100, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.blue)), 100, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        termsTextView.text = spannableString
        termsTextView.movementMethod = LinkMovementMethod.getInstance()
        termsTextView.highlightColor = Color.TRANSPARENT
    }
}
