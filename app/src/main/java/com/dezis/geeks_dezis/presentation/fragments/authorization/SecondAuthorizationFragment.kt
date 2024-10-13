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
        binding.btnRegister.setOnClickListener {
            if (validateInputs()) {
                findNavController().navigate(R.id.action_secondAuthorizationFragment_to_codeVerificationFragment)
            }
        }
        setupClickableText()
    }

    private fun setupClickableText() {
        val termsTextView = binding.termsOfSale
        val spannableString = SpannableString(
            getString(R.string.policy_txt)
        )

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

        spannableString.setSpan(privacyPolicyClickable, 100,spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.blue)), 100, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        termsTextView.text = spannableString
        termsTextView.movementMethod = LinkMovementMethod.getInstance()
        termsTextView.highlightColor = Color.TRANSPARENT
    }


    private fun validateFields() {
        val isAllFieldsValid =
            binding.etAddress.text.toString().isNotEmpty() &&
            binding.etNumFlat.text.toString().isNotEmpty()
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
        binding.tilAddress.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
        binding.tilNumFlat.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
    }
    private fun resetBorderColor() {
        binding.tilAddress.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
        binding.tilNumFlat.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
    }
}