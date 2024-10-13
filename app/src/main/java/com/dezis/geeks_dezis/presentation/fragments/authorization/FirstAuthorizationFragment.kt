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
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dezis.geeks_dezis.core.extensions.Extensions
import com.dezis.geeks_dezis.databinding.FragmentFirstAuthorizationBinding

class FirstAuthorizationFragment : BaseFragment<FragmentFirstAuthorizationBinding, FirstAuthorizationViewModel>(R.layout.fragment_first_authorization) {
    override val binding: FragmentFirstAuthorizationBinding by viewBinding(
        FragmentFirstAuthorizationBinding::bind
    )

    override val viewModel: FirstAuthorizationViewModel by viewModels()

    override fun constructorListeners() {
        binding.etName.addTextChangedListener { validateFields() }
        binding.etPassword.addTextChangedListener { validateFields() }
        binding.etEmail.addTextChangedListener { validateFields() }

        binding.btnContinue.setOnClickListener {
            if (validateInputs()){
                findNavController().navigate(R.id.action_authorizationFragment_to_secondAuthorizationFragment)
            }
        }
        setupClickableText()

    }

    private fun validateFields() {
        val isAllFieldsValid =
            binding.etName.text.toString().isNotEmpty() &&
            binding.etPassword.text.toString().isNotEmpty() &&
            binding.etEmail.text.toString().isNotEmpty()

        if (isAllFieldsValid) {
            binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
        } else {
            binding.btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey))
        }
    }

    private fun validateInputs():Boolean{
        var isValid = true
        if (binding.etName.text.toString().isEmpty()){
            binding.tilName.error = " "
            isValid = false
        }else{
            binding.tilEmail.error = null
        }
        if (binding.etEmail.text.toString().isEmpty()){
            binding.tilEmail.error = " "
            isValid = false
        }else{
            binding.tilEmail.error = null
        }
        if (binding.etPassword.text.toString().isEmpty()){
            binding.tilPassword.error = " "
        }else{
            binding.tilPassword.error = null
        }
        if (!isValid) {
            setErrorBorderColor()
        } else {
            resetBorderColor()
        }
        return isValid

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


    private fun setErrorBorderColor() {
        binding.tilName.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
        binding.tilEmail.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.red)
    }

    private fun resetBorderColor() {
        binding.tilName.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
        binding.tilEmail.boxStrokeColor = ContextCompat.getColor(requireContext(), R.color.transparent)
    }
}
